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

import com.vodoo.vodooapiadmin.dto.EnvTypeDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IEnvTypeService;
import com.vodoo.vodooapipersistance.model.EnvType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class EnvTypeEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IEnvTypeService envTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/envTypes")
    @ApiOperation(
        value = "Get all envTypes",
        notes = "Returns first N envTypes specified by the size parameter with page offset specified by page parameter.",
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
                List<EnvTypeDto> envTypesDto =
                envTypeService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(envTypesDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/envType/{id}")
    @ApiOperation(
        value = "Get envType by id",
        notes = "Returns envType for id specified.",
        response = EnvTypeDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "EnvType not found")})
    public ResponseEntity get(@ApiParam("EnvType id") @PathVariable("id") String id) {

        EnvTypeDto envTypeDto = null;
        try {
            envTypeDto = convertToDto(envTypeService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(envTypeDto);
    }

    @PostMapping(path = "/v1/envType",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new envType",
        notes = "Creates new envType. Returns created envType with id.",
        response = EnvTypeDto.class)
    public ResponseEntity add(
        @ApiParam("EnvType") @Valid @RequestBody EnvTypeDto envTypeDto){

        try {
            envTypeDto = convertToDto(envTypeService.add(convertToEntity(envTypeDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(envTypeDto);
    }


    @PutMapping(path = "/v1/envType",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing envType",
        notes = "Updates exisitng envType. Returns updated envType.",
        response = EnvTypeDto.class)
    public ResponseEntity update(
        @ApiParam("EnvType") @Valid @RequestBody EnvTypeDto envTypeDto){

        try {
            envTypeDto = convertToDto(envTypeService.update(convertToEntity(envTypeDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(envTypeDto);
    }

    @DeleteMapping(path = "/v1/envType/{id}")
    @ApiOperation(
        value = "Delete envType",
        notes = "Delete envType. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("envType id") @PathVariable("id") String id){

        boolean result;

        try {
            result = envTypeService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private EnvTypeDto convertToDto(EnvType envType) {
        return modelMapper.map(envType, EnvTypeDto.class);
    }

    private EnvType convertToEntity(EnvTypeDto envTypeDto) {
        return modelMapper.map(envTypeDto, EnvType.class);
    }
}
