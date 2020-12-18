package com.vodoo.vodooapiadmin.endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.vodoo.vodooapiadmin.dto.ProfileFonctionsDto;
import com.vodoo.vodooapiadmin.dto.error.CustomException;
import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
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

import com.vodoo.vodooapiadmin.dto.ProfilFoncDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IProfilFoncService;
import com.vodoo.vodooapipersistance.model.ProfilFonc;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class ProfilFoncEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;
    Logger logger = LoggerFactory.getLogger(TemplateAddonsEndpoint.class);

    @Autowired
    private IProfilFoncService profilFoncService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/profilFoncs")
    @ApiOperation(
        value = "Get all profilFoncs",
        notes = "Returns first N profilFoncs specified by the size parameter with page offset specified by page parameter.",
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
                List<ProfilFoncDto> profilFoncsDto =
                profilFoncService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(profilFoncsDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/profilFonc/{id}")
    @ApiOperation(
        value = "Get profilFonc by id",
        notes = "Returns profilFonc for id specified.",
        response = ProfilFoncDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ProfilFonc not found")})
    public ResponseEntity get(@ApiParam("ProfilFonc id") @PathVariable("id") String id) {

        ProfilFoncDto profilFoncDto = null;
        try {
            profilFoncDto = convertToDto(profilFoncService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profilFoncDto);
    }

    @PostMapping(path = "/v1/profilFonc",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new profilFonc",
        notes = "Creates new profilFonc. Returns created profilFonc with id.",
        response = ProfilFoncDto.class)
    public ResponseEntity add(
        @ApiParam("ProfilFonc") @Valid @RequestBody ProfilFoncDto profilFoncDto){

        try {
            profilFoncDto = convertToDto(profilFoncService.add(convertToEntity(profilFoncDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profilFoncDto);
    }


    @PutMapping(path = "/v1/profilFonc",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing profilFonc",
        notes = "Updates exisitng profilFonc. Returns updated profilFonc.",
        response = ProfilFoncDto.class)
    public ResponseEntity update(
        @ApiParam("ProfilFonc") @Valid @RequestBody ProfilFoncDto profilFoncDto){

        try {
            profilFoncDto = convertToDto(profilFoncService.update(convertToEntity(profilFoncDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(profilFoncDto);
    }

    @DeleteMapping(path = "/v1/profilFonc/{id}")
    @ApiOperation(
        value = "Delete profilFonc",
        notes = "Delete profilFonc. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("profilFonc id") @PathVariable("id") String id){

        boolean result;

        try {
            result = profilFoncService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private ProfilFoncDto convertToDto(ProfilFonc profilFonc) {
        return modelMapper.map(profilFonc, ProfilFoncDto.class);
    }

    private ProfilFonc convertToEntity(ProfilFoncDto profilFoncDto) {
        return modelMapper.map(profilFoncDto, ProfilFonc.class);
    }
    @PostMapping("/v1/ProfileFonctions/affectFonctionsToProfile/{idProfile}/{idFonction}")
    @ApiOperation(
            value = "Affectation profileFonctions",
            notes = "Affectation profileFonctions. Returns Boolean.",
            response = Boolean.class)
    public Object affectFonctionsToProfile(HttpServletRequest request, @RequestBody @Valid ProfileFonctionsDto ProfilFoncDto) {
        logger.info("TemplateEndPoint GetListTemplate Start");

        // TokenValidationOutput res =
        // tokenValidationOutputService.getValidate(request);
        try {
            // if(res.getResult()== ApiConstants.TOKEN_VALID) {
            // logger.info("UserEndpoint.getListUser TOKEN_VALID");
            ObjectResponse response = profilFoncService.affectFonctionsToProfile(ProfilFoncDto.getIdsFonctions(),ProfilFoncDto.getIdProfile());
            return response;
            // } else {
            // logger.info("UserEndpoint.getListUser TOKEN MISSING");
            // return res;
            // }
        } catch (Exception e) {
            logger.error("ProfileEndPoint.GetListProfile Exception: " + e.getCause() + " " + e.getMessage());
            return new CustomException(e.getMessage(), e.getCause());
        }

    }
}
