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

import com.vodoo.vodooapiadmin.dto.TokenDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ITokenService;
import com.vodoo.vodooapipersistance.model.Token;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class TokenEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/v1/tokens")
    @ApiOperation(
        value = "Get all tokens",
        notes = "Returns first N tokens specified by the size parameter with page offset specified by page parameter.",
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
                List<TokenDto> tokensDto =
                tokenService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(tokensDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/token/{id}")
    @ApiOperation(
        value = "Get token by id",
        notes = "Returns token for id specified.",
        response = TokenDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Token not found")})
    public ResponseEntity get(@ApiParam("Token id") @PathVariable("id") String id) {

        TokenDto tokenDto = null;
        try {
            tokenDto = convertToDto(tokenService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(tokenDto);
    }

    @PostMapping(path = "/v1/token",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Create new token",
        notes = "Creates new token. Returns created token with id.",
        response = TokenDto.class)
    public ResponseEntity add(
        @ApiParam("Token") @Valid @RequestBody TokenDto tokenDto){

        try {
            tokenDto = convertToDto(tokenService.add(convertToEntity(tokenDto)));
        } catch (CreateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(tokenDto);
    }


    @PutMapping(path = "/v1/token",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing token",
        notes = "Updates exisitng token. Returns updated token.",
        response = TokenDto.class)
    public ResponseEntity update(
        @ApiParam("Token") @Valid @RequestBody TokenDto tokenDto){

        try {
            tokenDto = convertToDto(tokenService.update(convertToEntity(tokenDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(tokenDto);
    }

    @DeleteMapping(path = "/v1/token/{id}")
    @ApiOperation(
        value = "Delete token",
        notes = "Delete token. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("token id") @PathVariable("id") String id){

        boolean result;

        try {
            result = tokenService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private TokenDto convertToDto(Token token) {
        return modelMapper.map(token, TokenDto.class);
    }

    private Token convertToEntity(TokenDto tokenDto) {
        return modelMapper.map(tokenDto, Token.class);
    }
}
