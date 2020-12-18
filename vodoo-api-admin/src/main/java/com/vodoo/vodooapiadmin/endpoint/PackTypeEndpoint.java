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

import com.vodoo.vodooapiadmin.dto.PackTypeDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IPackTypeService;
import com.vodoo.vodooapipersistance.model.PackType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class PackTypeEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IPackTypeService packTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/packTypes")
    @ApiOperation(
        value = "Get all packTypes",
        notes = "Returns first N packTypes specified by the size parameter with page offset specified by page parameter.",
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
                List<PackTypeDto> packTypesDto =
                packTypeService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(packTypesDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/packType/{id}")
    @ApiOperation(
        value = "Get packType by id",
        notes = "Returns packType for id specified.",
        response = PackTypeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "PackType not found")})
    public ResponseEntity get(@ApiParam("PackType id") @PathVariable("id") String id) {

        PackTypeDto packTypeDto = null;
        try {
            packTypeDto = convertToDto(packTypeService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(packTypeDto);
    }

    @PostMapping(path = "/v1/packType",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new packType",
        notes = "Creates new packType. Returns created packType with id.",
        response = PackTypeDto.class)
    public ResponseEntity add(
        @ApiParam("PackType") @Valid @RequestBody PackTypeDto packTypeDto){

        try {
            packTypeDto = convertToDto(packTypeService.add(convertToEntity(packTypeDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(packTypeDto);
    }


    @PutMapping(path = "/v1/packType",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing packType",
        notes = "Updates exisitng packType. Returns updated packType.",
        response = PackTypeDto.class)
    public ResponseEntity update(
        @ApiParam("PackType") @Valid @RequestBody PackTypeDto packTypeDto){

        try {
            packTypeDto = convertToDto(packTypeService.update(convertToEntity(packTypeDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(packTypeDto);
    }

    @DeleteMapping(path = "/v1/packType/{id}")
    @ApiOperation(
        value = "Delete packType",
        notes = "Delete packType. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("packType id") @PathVariable("id") String id){

        boolean result;

        try {
            result = packTypeService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private PackTypeDto convertToDto(PackType packType) {
        return modelMapper.map(packType, PackTypeDto.class);
    }

    private PackType convertToEntity(PackTypeDto packTypeDto) {
        return modelMapper.map(packTypeDto, PackType.class);
    }
}
