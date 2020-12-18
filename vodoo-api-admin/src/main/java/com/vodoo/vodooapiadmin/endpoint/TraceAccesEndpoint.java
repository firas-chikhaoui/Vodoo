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

import com.vodoo.vodooapiadmin.dto.TraceAccesDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ITraceAccesService;
import com.vodoo.vodooapipersistance.model.TraceAcces;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class TraceAccesEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ITraceAccesService traceAccesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/traceAccess")
    @ApiOperation(
        value = "Get all traceAccess",
        notes = "Returns first N traceAccess specified by the size parameter with page offset specified by page parameter.",
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
                List<TraceAccesDto> traceAccessDto =
                traceAccesService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(traceAccessDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/traceAcces/{id}")
    @ApiOperation(
        value = "Get traceAcces by id",
        notes = "Returns traceAcces for id specified.",
        response = TraceAccesDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "TraceAcces not found")})
    public ResponseEntity get(@ApiParam("TraceAcces id") @PathVariable("id") String id) {

        TraceAccesDto traceAccesDto = null;
        try {
            traceAccesDto = convertToDto(traceAccesService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(traceAccesDto);
    }

    @PostMapping(path = "/v1/traceAcces",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new traceAcces",
        notes = "Creates new traceAcces. Returns created traceAcces with id.",
        response = TraceAccesDto.class)
    public ResponseEntity add(
        @ApiParam("TraceAcces") @Valid @RequestBody TraceAccesDto traceAccesDto){

        try {
            traceAccesDto = convertToDto(traceAccesService.add(convertToEntity(traceAccesDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(traceAccesDto);
    }


    @PutMapping(path = "/v1/traceAcces",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing traceAcces",
        notes = "Updates exisitng traceAcces. Returns updated traceAcces.",
        response = TraceAccesDto.class)
    public ResponseEntity update(
        @ApiParam("TraceAcces") @Valid @RequestBody TraceAccesDto traceAccesDto){

        try {
            traceAccesDto = convertToDto(traceAccesService.update(convertToEntity(traceAccesDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(traceAccesDto);
    }

    @DeleteMapping(path = "/v1/traceAcces/{id}")
    @ApiOperation(
        value = "Delete traceAcces",
        notes = "Delete traceAcces. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("traceAcces id") @PathVariable("id") String id){

        boolean result;

        try {
            result = traceAccesService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private TraceAccesDto convertToDto(TraceAcces traceAcces) {
        return modelMapper.map(traceAcces, TraceAccesDto.class);
    }

    private TraceAcces convertToEntity(TraceAccesDto traceAccesDto) {
        return modelMapper.map(traceAccesDto, TraceAcces.class);
    }
}
