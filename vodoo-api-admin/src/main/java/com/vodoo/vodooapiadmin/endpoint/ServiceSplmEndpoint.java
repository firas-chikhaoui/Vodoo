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

import com.vodoo.vodooapiadmin.dto.ServiceSplmDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IServiceSplmService;
import com.vodoo.vodooapipersistance.model.ServiceSplm;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class ServiceSplmEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IServiceSplmService serviceSplmService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/serviceSplms")
    @ApiOperation(
        value = "Get all serviceSplms",
        notes = "Returns first N serviceSplms specified by the size parameter with page offset specified by page parameter.",
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
                List<ServiceSplmDto> serviceSplmsDto =
                serviceSplmService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(serviceSplmsDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/serviceSplm/{id}")
    @ApiOperation(
        value = "Get serviceSplm by id",
        notes = "Returns serviceSplm for id specified.",
        response = ServiceSplmDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ServiceSplm not found")})
    public ResponseEntity get(@ApiParam("ServiceSplm id") @PathVariable("id") String id) {

        ServiceSplmDto serviceSplmDto = null;
        try {
            serviceSplmDto = convertToDto(serviceSplmService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(serviceSplmDto);
    }

    @PostMapping(path = "/v1/serviceSplm",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new serviceSplm",
        notes = "Creates new serviceSplm. Returns created serviceSplm with id.",
        response = ServiceSplmDto.class)
    public ResponseEntity add(
        @ApiParam("ServiceSplm") @Valid @RequestBody ServiceSplmDto serviceSplmDto){

        try {
            serviceSplmDto = convertToDto(serviceSplmService.add(convertToEntity(serviceSplmDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(serviceSplmDto);
    }


    @PutMapping(path = "/v1/serviceSplm",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing serviceSplm",
        notes = "Updates exisitng serviceSplm. Returns updated serviceSplm.",
        response = ServiceSplmDto.class)
    public ResponseEntity update(
        @ApiParam("ServiceSplm") @Valid @RequestBody ServiceSplmDto serviceSplmDto){

        try {
            serviceSplmDto = convertToDto(serviceSplmService.update(convertToEntity(serviceSplmDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(serviceSplmDto);
    }

    @DeleteMapping(path = "/v1/serviceSplm/{id}")
    @ApiOperation(
        value = "Delete serviceSplm",
        notes = "Delete serviceSplm. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("serviceSplm id") @PathVariable("id") String id){

        boolean result;

        try {
            result = serviceSplmService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private ServiceSplmDto convertToDto(ServiceSplm serviceSplm) {
        return modelMapper.map(serviceSplm, ServiceSplmDto.class);
    }

    private ServiceSplm convertToEntity(ServiceSplmDto serviceSplmDto) {
        return modelMapper.map(serviceSplmDto, ServiceSplm.class);
    }
}
