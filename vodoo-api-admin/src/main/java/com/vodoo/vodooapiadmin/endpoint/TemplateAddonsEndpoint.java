package com.vodoo.vodooapiadmin.endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.vodoo.vodooapiadmin.dto.TemplateAddonssDto;
import com.vodoo.vodooapiadmin.dto.error.CustomException;
import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.vodoo.vodooapiadmin.dto.TemplateAddonsDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ITemplateAddonsService;
import com.vodoo.vodooapipersistance.model.TemplateAddons;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class TemplateAddonsEndpoint extends BaseEndpoint {

    Logger logger = LoggerFactory.getLogger(TemplateAddonsEndpoint.class);

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ITemplateAddonsService templateAddonsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/templateAddonss")
    @ApiOperation(
        value = "Get all templateAddonss",
        notes = "Returns first N templateAddonss specified by the size parameter with page offset specified by page parameter.",
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
                List<TemplateAddonsDto> templateAddonssDto =
                templateAddonsService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(templateAddonssDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/templateAddons/{id}")
    @ApiOperation(
        value = "Get templateAddons by id",
        notes = "Returns templateAddons for id specified.",
        response = TemplateAddonsDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "TemplateAddons not found")})
    public ResponseEntity get(@ApiParam("TemplateAddons id") @PathVariable("id") String id) {

        TemplateAddonsDto templateAddonsDto = null;
        try {
            templateAddonsDto = convertToDto(templateAddonsService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateAddonsDto);
    }

    @PostMapping(path = "/v1/templateAddons",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new templateAddons",
        notes = "Creates new templateAddons. Returns created templateAddons with id.",
        response = TemplateAddonsDto.class)
    public ResponseEntity add(
        @ApiParam("TemplateAddons") @Valid @RequestBody TemplateAddonsDto templateAddonsDto){

        try {
            templateAddonsDto = convertToDto(templateAddonsService.add(convertToEntity(templateAddonsDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateAddonsDto);
    }


    @PutMapping(path = "/v1/templateAddons",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing templateAddons",
        notes = "Updates exisitng templateAddons. Returns updated templateAddons.",
        response = TemplateAddonsDto.class)
    public ResponseEntity update(
        @ApiParam("TemplateAddons") @Valid @RequestBody TemplateAddonsDto templateAddonsDto){

        try {
            templateAddonsDto = convertToDto(templateAddonsService.update(convertToEntity(templateAddonsDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateAddonsDto);
    }

    @DeleteMapping(path = "/v1/templateAddons/{id}")
    @ApiOperation(
        value = "Delete templateAddons",
        notes = "Delete templateAddons. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("templateAddons id") @PathVariable("id") String id){

        boolean result;

        try {
            result = templateAddonsService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }

    @PostMapping("/v1/templateAddons/affectAddonsToTemp/{idTemplate}/{idAddons}")
    @ApiOperation(
            value = "Affectation templateAddons",
            notes = "Affectation templateAddons. Returns Boolean.",
            response = Boolean.class)
    public Object affectAddonsToTemp(HttpServletRequest request, @RequestBody @Valid TemplateAddonssDto templateAddonssDto) {
        logger.info("TemplateEndPoint GetListTemplate Start");

        // TokenValidationOutput res =
        // tokenValidationOutputService.getValidate(request);
        try {
            // if(res.getResult()== ApiConstants.TOKEN_VALID) {
            // logger.info("UserEndpoint.getListUser TOKEN_VALID");
            ObjectResponse response = templateAddonsService.affectAddonsToTemp(templateAddonssDto.getIdsAddons(),templateAddonssDto.getIdTemplate());
            return response;
            // } else {
            // logger.info("UserEndpoint.getListUser TOKEN MISSING");
            // return res;
            // }
        } catch (Exception e) {
            logger.error("TemplateEndPoint.GetListTemplate Exception: " + e.getCause() + " " + e.getMessage());
            return new CustomException(e.getMessage(), e.getCause());
        }

    }


    private TemplateAddonsDto convertToDto(TemplateAddons templateAddons) {
        return modelMapper.map(templateAddons, TemplateAddonsDto.class);
    }

    private TemplateAddons convertToEntity(TemplateAddonsDto templateAddonsDto) {
        return modelMapper.map(templateAddonsDto, TemplateAddons.class);
    }
}
