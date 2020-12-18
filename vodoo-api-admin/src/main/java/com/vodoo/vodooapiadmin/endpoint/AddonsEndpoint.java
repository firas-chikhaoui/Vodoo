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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vodoo.vodooapiadmin.dto.AddonsDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IAddonsService;
import com.vodoo.vodooapipersistance.model.Addons;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class AddonsEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IAddonsService addonsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/addonss")
    @ApiOperation(
        value = "Get all addonss",
        notes = "Returns first N addonss specified by the size parameter with page offset specified by page parameter.",
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
                List<AddonsDto> addonssDto =
                addonsService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(addonssDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/addons/{id}")
    @ApiOperation(
        value = "Get addons by id",
        notes = "Returns addons for id specified.",
        response = AddonsDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Addons not found")})
    public ResponseEntity get(@ApiParam("Addons id") @PathVariable("id") String id) {

        AddonsDto addonsDto = null;
        try {
            addonsDto = convertToDto(addonsService.findOne(id));
        } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(addonsDto);
    }

    @PostMapping(path = "/v1/addons",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new addons",
        notes = "Creates new addons. Returns created addons with id.",
        response = AddonsDto.class)
    public ResponseEntity add(
        @ApiParam("Addons") @Valid @RequestBody AddonsDto addonsDto){

        try {
            addonsDto = convertToDto(addonsService.add(convertToEntity(addonsDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(addonsDto);
    }


    @PutMapping(path = "/v1/addons",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing addons",
        notes = "Updates exisitng addons. Returns updated addons.",
        response = AddonsDto.class)
    public ResponseEntity update(
        @ApiParam("Addons") @Valid @RequestBody AddonsDto addonsDto){

        try {
            addonsDto = convertToDto(addonsService.update(convertToEntity(addonsDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(addonsDto);
    }

    @DeleteMapping(path = "/v1/addons/{id}")
    @ApiOperation(
        value = "Delete addons",
        notes = "Delete addons. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("addons id") @PathVariable("id") String id){

        boolean result;

        try {
            result = addonsService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private AddonsDto convertToDto(Addons addons) {
        return modelMapper.map(addons, AddonsDto.class);
    }

    private Addons convertToEntity(AddonsDto addonsDto) {
        return modelMapper.map(addonsDto, Addons.class);
    }
}
