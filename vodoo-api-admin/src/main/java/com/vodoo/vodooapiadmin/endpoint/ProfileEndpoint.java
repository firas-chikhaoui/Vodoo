package com.vodoo.vodooapiadmin.endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.vodoo.vodooapiadmin.dto.ProfileDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IProfileService;
import com.vodoo.vodooapipersistance.model.Profile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class ProfileEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IProfileService profileService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/profiles")
    @ApiOperation(
        value = "Get all profiles",
        notes = "Returns first N profiles specified by the size parameter with page offset specified by page parameter.",
        response = Collection.class)
    public ResponseEntity getAll(
        @ApiParam("The size of the page to be returned") @RequestParam(required = false) Integer size,
        @ApiParam("Zero-based page index") @RequestParam(required = false) Integer page) {

        if (size == null) {
            size = DEFAULT_PAGE_SIZE;
        }
        if (page == null) {
            page = 0;
        }

        Pageable pageable = PageRequest.of(page, size);

        try {
                List<ProfileDto> profilesDto =
                profileService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(profilesDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/profile/{id}")
    @ApiOperation(
        value = "Get profile by id",
        notes = "Returns profile for id specified.",
        response = ProfileDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Profile not found")})
    public ResponseEntity get(@ApiParam("Profile id") @PathVariable("id") String id) {

        ProfileDto profileDto = null;
        try {
            profileDto = convertToDto(profileService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profileDto);
    }

    @PostMapping(path = "/v1/profile",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new profile",
        notes = "Creates new profile. Returns created profile with id.",
        response = ProfileDto.class)
    public ResponseEntity add(
        @ApiParam("Profile") @Valid @RequestBody ProfileDto profileDto){

        try {
            profileDto = convertToDto(profileService.add(convertToEntity(profileDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profileDto);
    }


    @PutMapping(path = "/v1/profile",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing profile",
        notes = "Updates exisitng profile. Returns updated profile.",
        response = ProfileDto.class)
    public ResponseEntity update(
        @ApiParam("Profile") @Valid @RequestBody ProfileDto profileDto){

        try {
            profileDto = convertToDto(profileService.update(convertToEntity(profileDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profileDto);
    }

    @DeleteMapping(path = "/v1/profile/{id}")
    @ApiOperation(
        value = "Delete profile",
        notes = "Delete profile. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("profile id") @PathVariable("id") String id){

        boolean result;

        try {
            result = profileService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private ProfileDto convertToDto(Profile profile) {
        return modelMapper.map(profile, ProfileDto.class);
    }

    private Profile convertToEntity(ProfileDto profileDto) {
        return modelMapper.map(profileDto, Profile.class);
    }
}
