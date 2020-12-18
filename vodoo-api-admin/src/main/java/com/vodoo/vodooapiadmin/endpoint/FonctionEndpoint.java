package com.vodoo.vodooapiadmin.endpoint;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.*;

import com.vodoo.vodooapiadmin.dto.FonctionDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IFonctionService;
import com.vodoo.vodooapipersistance.model.Fonction;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class FonctionEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IFonctionService fonctionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/fonctions")
    @ApiOperation(
        value = "Get all fonctions",
        notes = "Returns first N fonctions specified by the size parameter with page offset specified by page parameter.",
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
                List<FonctionDto> fonctionsDto =
                fonctionService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(fonctionsDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/fonction/{id}")
    @ApiOperation(
        value = "Get fonction by id",
        notes = "Returns fonction for id specified.",
        response = FonctionDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Fonction not found")})
    public ResponseEntity get(@ApiParam("Fonction id") @PathVariable("id") String id) {

        FonctionDto fonctionDto = null;
        try {
            fonctionDto = convertToDto(fonctionService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(fonctionDto);
    }

    @GetMapping(path = "/v1/getfonctionsbyidutilisateur/{id}")
    @ApiOperation(
            value = "Get fonctions by idutilisateur",
            notes = "Returns fonctions for id specified.",
            response = FonctionDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Fonction not found")})
    public ResponseEntity getfonctions(@ApiParam("utilisateur id") @PathVariable("id") String id) {

        List<Fonction> fonctionsList = new ArrayList<>();
        FonctionDto fonctionDto = null;
        try {
            fonctionsList = fonctionService.getFonctionsByUser(id);
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(fonctionDto);
    }

    @PostMapping(path = "/v1/fonction",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new fonction",
        notes = "Creates new fonction. Returns created fonction with id.",
        response = FonctionDto.class)
    public ResponseEntity add(
        @ApiParam("Fonction") @Valid @RequestBody FonctionDto fonctionDto){

        try {
            fonctionDto = convertToDto(fonctionService.add(convertToEntity(fonctionDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(fonctionDto);
    }


    @PutMapping(path = "/v1/fonction",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing fonction",
        notes = "Updates exisitng fonction. Returns updated fonction.",
        response = FonctionDto.class)
    public ResponseEntity update(
        @ApiParam("Fonction") @Valid @RequestBody FonctionDto fonctionDto){

        try {
            fonctionDto = convertToDto(fonctionService.update(convertToEntity(fonctionDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(fonctionDto);
    }

    @DeleteMapping(path = "/v1/fonction/{id}")
    @ApiOperation(
        value = "Delete fonction",
        notes = "Delete fonction. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("fonction id") @PathVariable("id") String id){

        boolean result;

        try {
            result = fonctionService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private FonctionDto convertToDto(Fonction fonction) {
        return modelMapper.map(fonction, FonctionDto.class);
    }

    private Fonction convertToEntity(FonctionDto fonctionDto) {
        return modelMapper.map(fonctionDto, Fonction.class);
    }
}
