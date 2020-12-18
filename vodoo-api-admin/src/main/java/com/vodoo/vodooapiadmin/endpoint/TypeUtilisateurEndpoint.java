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

import com.vodoo.vodooapiadmin.dto.TypeUtilisateurDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ITypeUtilisateurService;
import com.vodoo.vodooapipersistance.model.TypeUtilisateur;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class TypeUtilisateurEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ITypeUtilisateurService typeUtilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/typeUtilisateurs")
    @ApiOperation(
        value = "Get all typeUtilisateurs",
        notes = "Returns first N typeUtilisateurs specified by the size parameter with page offset specified by page parameter.",
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
                List<TypeUtilisateurDto> typeUtilisateursDto =
                typeUtilisateurService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(typeUtilisateursDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/typeUtilisateur/{id}")
    @ApiOperation(
        value = "Get typeUtilisateur by id",
        notes = "Returns typeUtilisateur for id specified.",
        response = TypeUtilisateurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "TypeUtilisateur not found")})
    public ResponseEntity get(@ApiParam("TypeUtilisateur id") @PathVariable("id") String id) {

        TypeUtilisateurDto typeUtilisateurDto = null;
        try {
            typeUtilisateurDto = convertToDto(typeUtilisateurService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(typeUtilisateurDto);
    }

    @PostMapping(path = "/v1/typeUtilisateur",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new typeUtilisateur",
        notes = "Creates new typeUtilisateur. Returns created typeUtilisateur with id.",
        response = TypeUtilisateurDto.class)
    public ResponseEntity add(
        @ApiParam("TypeUtilisateur") @Valid @RequestBody TypeUtilisateurDto typeUtilisateurDto){

        try {
            typeUtilisateurDto = convertToDto(typeUtilisateurService.add(convertToEntity(typeUtilisateurDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(typeUtilisateurDto);
    }


    @PutMapping(path = "/v1/typeUtilisateur",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing typeUtilisateur",
        notes = "Updates exisitng typeUtilisateur. Returns updated typeUtilisateur.",
        response = TypeUtilisateurDto.class)
    public ResponseEntity update(
        @ApiParam("TypeUtilisateur") @Valid @RequestBody TypeUtilisateurDto typeUtilisateurDto){

        try {
            typeUtilisateurDto = convertToDto(typeUtilisateurService.update(convertToEntity(typeUtilisateurDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(typeUtilisateurDto);
    }

    @DeleteMapping(path = "/v1/typeUtilisateur/{id}")
    @ApiOperation(
        value = "Delete typeUtilisateur",
        notes = "Delete typeUtilisateur. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("typeUtilisateur id") @PathVariable("id") String id){

        boolean result;

        try {
            result = typeUtilisateurService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private TypeUtilisateurDto convertToDto(TypeUtilisateur typeUtilisateur) {
        return modelMapper.map(typeUtilisateur, TypeUtilisateurDto.class);
    }

    private TypeUtilisateur convertToEntity(TypeUtilisateurDto typeUtilisateurDto) {
        return modelMapper.map(typeUtilisateurDto, TypeUtilisateur.class);
    }
}
