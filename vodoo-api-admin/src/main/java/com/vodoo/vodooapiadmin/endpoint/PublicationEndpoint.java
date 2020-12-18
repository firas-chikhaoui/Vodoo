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

import com.vodoo.vodooapiadmin.dto.PublicationDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IPublicationService;
import com.vodoo.vodooapipersistance.model.Publication;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class PublicationEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IPublicationService publicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/publications")
    @ApiOperation(
        value = "Get all publications",
        notes = "Returns first N publications specified by the size parameter with page offset specified by page parameter.",
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
                List<PublicationDto> publicationsDto =
                publicationService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(publicationsDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/publication/{id}")
    @ApiOperation(
        value = "Get publication by id",
        notes = "Returns publication for id specified.",
        response = PublicationDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Publication not found")})
    public ResponseEntity get(@ApiParam("Publication id") @PathVariable("id") String id) {

        PublicationDto publicationDto = null;
        try {
            publicationDto = convertToDto(publicationService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(publicationDto);
    }

    @PostMapping(path = "/v1/publication",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new publication",
        notes = "Creates new publication. Returns created publication with id.",
        response = PublicationDto.class)
    public ResponseEntity add(
        @ApiParam("Publication") @Valid @RequestBody PublicationDto publicationDto){

        try {
            publicationDto = convertToDto(publicationService.add(convertToEntity(publicationDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(publicationDto);
    }


    @PutMapping(path = "/v1/publication",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing publication",
        notes = "Updates exisitng publication. Returns updated publication.",
        response = PublicationDto.class)
    public ResponseEntity update(
        @ApiParam("Publication") @Valid @RequestBody PublicationDto publicationDto){

        try {
            publicationDto = convertToDto(publicationService.update(convertToEntity(publicationDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(publicationDto);
    }

    @DeleteMapping(path = "/v1/publication/{id}")
    @ApiOperation(
        value = "Delete publication",
        notes = "Delete publication. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("publication id") @PathVariable("id") String id){

        boolean result;

        try {
            result = publicationService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private PublicationDto convertToDto(Publication publication) {
        return modelMapper.map(publication, PublicationDto.class);
    }

    private Publication convertToEntity(PublicationDto publicationDto) {
        return modelMapper.map(publicationDto, Publication.class);
    }
}
