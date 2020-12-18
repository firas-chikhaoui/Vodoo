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

import com.vodoo.vodooapiadmin.dto.TemplateDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ITemplateService;
import com.vodoo.vodooapipersistance.model.Template;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class TemplateEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/templates")
    @ApiOperation(
        value = "Get all templates",
        notes = "Returns first N templates specified by the size parameter with page offset specified by page parameter.",
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
                List<TemplateDto> templatesDto =
                templateService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(templatesDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/template/{id}")
    @ApiOperation(
        value = "Get template by id",
        notes = "Returns template for id specified.",
        response = TemplateDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Template not found")})
    public ResponseEntity get(@ApiParam("Template id") @PathVariable("id") String id) {

        TemplateDto templateDto = null;
        try {
            templateDto = convertToDto(templateService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateDto);
    }

    @PostMapping(path = "/v1/template",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new template",
        notes = "Creates new template. Returns created template with id.",
        response = TemplateDto.class)
    public ResponseEntity add(
        @ApiParam("Template") @Valid @RequestBody TemplateDto templateDto){

        try {
            templateDto = convertToDto(templateService.add(convertToEntity(templateDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateDto);
    }


    @PutMapping(path = "/v1/template",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing template",
        notes = "Updates exisitng template. Returns updated template.",
        response = TemplateDto.class)
    public ResponseEntity update(
        @ApiParam("Template") @Valid @RequestBody TemplateDto templateDto){

        try {
            templateDto = convertToDto(templateService.update(convertToEntity(templateDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(templateDto);
    }

    @DeleteMapping(path = "/v1/template/{id}")
    @ApiOperation(
        value = "Delete template",
        notes = "Delete template. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("template id") @PathVariable("id") String id){

        boolean result;

        try {
            result = templateService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private TemplateDto convertToDto(Template template) {
        return modelMapper.map(template, TemplateDto.class);
    }

    private Template convertToEntity(TemplateDto templateDto) {
        return modelMapper.map(templateDto, Template.class);
    }
}
