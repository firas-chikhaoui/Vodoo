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

import com.vodoo.vodooapiadmin.dto.SecteurTemplateDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.ISecteurTemplateService;
import com.vodoo.vodooapipersistance.model.SecteurTemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated // required for @Valid on method parameters such as @RequesParam, @PathVariable,
			// @RequestHeader
public class SecteurTemplateEndpoint extends BaseEndpoint {

	static final int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	private ISecteurTemplateService secteurTemplateService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/v1/secteurTemplates")
	@ApiOperation(value = "Get all secteurTemplates", notes = "Returns first N secteurTemplates specified by the size parameter with page offset specified by page parameter.", response = Collection.class)
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
			List<SecteurTemplateDto> secteurTemplatesDto = secteurTemplateService.findAll(pageable).stream()
					.map(this::convertToDto).collect(Collectors.toList());
			return ResponseEntity.ok().body(secteurTemplatesDto);
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}
	}

	@GetMapping(path = "/v1/secteurTemplate/{id}")
	@ApiOperation(value = "Get secteurTemplate by id", notes = "Returns secteurTemplate for id specified.", response = SecteurTemplateDto.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "SecteurTemplate not found") })
	public ResponseEntity get(@ApiParam("SecteurTemplate id") @PathVariable("id") String id) {

		SecteurTemplateDto secteurTemplateDto = null;
		try {
			secteurTemplateDto = convertToDto(secteurTemplateService.findOne(id));
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(secteurTemplateDto);
	}

	@PostMapping(path = "/v1/secteurTemplate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create new secteurTemplate", notes = "Creates new secteurTemplate. Returns created secteurTemplate with id.", response = SecteurTemplateDto.class)
	public ResponseEntity add(@ApiParam("SecteurTemplate") @Valid @RequestBody SecteurTemplateDto secteurTemplateDto) {

		try {
			secteurTemplateDto = convertToDto(secteurTemplateService.add(convertToEntity(secteurTemplateDto)));
		} catch (CreateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(secteurTemplateDto);
	}

	@PutMapping(path = "/v1/secteurTemplate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update existing secteurTemplate", notes = "Updates exisitng secteurTemplate. Returns updated secteurTemplate.", response = SecteurTemplateDto.class)
	public ResponseEntity update(
			@ApiParam("SecteurTemplate") @Valid @RequestBody SecteurTemplateDto secteurTemplateDto) {

		try {
			secteurTemplateDto = convertToDto(secteurTemplateService.update(convertToEntity(secteurTemplateDto)));
		} catch (UpdateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(secteurTemplateDto);
	}

	@DeleteMapping(path = "/v1/secteurTemplate/{id}")
	@ApiOperation(value = "Delete secteurTemplate", notes = "Delete secteurTemplate. Returns Boolean.", response = Boolean.class)
	public ResponseEntity delete(@ApiParam("secteurTemplate id") @PathVariable("id") String id) {

		boolean result;

		try {
			result = secteurTemplateService.delete(id);
		} catch (DeleteException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(Collections.singletonMap("result", result));
	}

	private SecteurTemplateDto convertToDto(SecteurTemplate secteurTemplate) {
		return modelMapper.map(secteurTemplate, SecteurTemplateDto.class);
	}

	private SecteurTemplate convertToEntity(SecteurTemplateDto secteurTemplateDto) {
		return modelMapper.map(secteurTemplateDto, SecteurTemplate.class);
	}
}
