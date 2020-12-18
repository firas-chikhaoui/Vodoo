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

import com.vodoo.vodooapiadmin.dto.SecteurDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ISecteurService;
import com.vodoo.vodooapipersistance.model.Secteur;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class SecteurEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ISecteurService secteurService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/secteurs")
    @ApiOperation(
        value = "Get all secteurs",
        notes = "Returns first N secteurs specified by the size parameter with page offset specified by page parameter.",
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
                List<SecteurDto> secteursDto =
                secteurService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(secteursDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/secteur/{id}")
    @ApiOperation(
        value = "Get secteur by id",
        notes = "Returns secteur for id specified.",
        response = SecteurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Secteur not found")})
    public ResponseEntity get(@ApiParam("Secteur id") @PathVariable("id") String id) {

        SecteurDto secteurDto = null;
        try {
            secteurDto = convertToDto(secteurService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(secteurDto);
    }

    @PostMapping(path = "/v1/secteur",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new secteur",
        notes = "Creates new secteur. Returns created secteur with id.",
        response = SecteurDto.class)
    public ResponseEntity add(
        @ApiParam("Secteur") @Valid @RequestBody SecteurDto secteurDto){

        try {
            secteurDto = convertToDto(secteurService.add(convertToEntity(secteurDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(secteurDto);
    }


    @PutMapping(path = "/v1/secteur",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing secteur",
        notes = "Updates exisitng secteur. Returns updated secteur.",
        response = SecteurDto.class)
    public ResponseEntity update(
        @ApiParam("Secteur") @Valid @RequestBody SecteurDto secteurDto){

        try {
            secteurDto = convertToDto(secteurService.update(convertToEntity(secteurDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(secteurDto);
    }

    @DeleteMapping(path = "/v1/secteur/{id}")
    @ApiOperation(
        value = "Delete secteur",
        notes = "Delete secteur. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("secteur id") @PathVariable("id") String id){

        boolean result;

        try {
            result = secteurService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private SecteurDto convertToDto(Secteur secteur) {
        return modelMapper.map(secteur, SecteurDto.class);
    }

    private Secteur convertToEntity(SecteurDto secteurDto) {
        return modelMapper.map(secteurDto, Secteur.class);
    }
}
