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

import com.vodoo.vodooapiadmin.dto.PackDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IPackService;
import com.vodoo.vodooapipersistance.model.Pack;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated // required for @Valid on method parameters such as @RequesParam, @PathVariable,
			// @RequestHeader
public class PackEndpoint extends BaseEndpoint {

	static final int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	private IPackService packService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/v1/packs")
	@ApiOperation(value = "Get all packs", notes = "Returns first N packs specified by the size parameter with page offset specified by page parameter.", response = Collection.class)
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
			List<PackDto> packsDto = packService.findAll(pageable).stream().map(this::convertToDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok().body(packsDto);
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}
	}

	@GetMapping(path = "/v1/pack/{id}")
	@ApiOperation(value = "Get pack by id", notes = "Returns pack for id specified.", response = PackDto.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Pack not found") })
	public ResponseEntity get(@ApiParam("Pack id") @PathVariable("id") String id) {

		PackDto packDto = null;
		try {
			packDto = convertToDto(packService.findOne(id));
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(packDto);
	}

	@PostMapping(path = "/v1/pack", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create new pack", notes = "Creates new pack. Returns created pack with id.", response = PackDto.class)
	public ResponseEntity add(@ApiParam("Pack") @Valid @RequestBody PackDto packDto) {

		try {
			packDto = convertToDto(packService.add(convertToEntity(packDto)));
		} catch (CreateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(packDto);
	}

	@PutMapping(path = "/v1/pack", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update existing pack", notes = "Updates exisitng pack. Returns updated pack.", response = PackDto.class)
	public ResponseEntity update(@ApiParam("Pack") @Valid @RequestBody PackDto packDto) {

		try {
			packDto = convertToDto(packService.update(convertToEntity(packDto)));
		} catch (UpdateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(packDto);
	}

	@DeleteMapping(path = "/v1/pack/{id}")
	@ApiOperation(value = "Delete pack", notes = "Delete pack. Returns Boolean.", response = Boolean.class)
	public ResponseEntity delete(@ApiParam("pack id") @PathVariable("id") String id) {

		boolean result;

		try {
			result = packService.delete(id);
		} catch (DeleteException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(Collections.singletonMap("result", result));
	}

	private PackDto convertToDto(Pack pack) {
		return modelMapper.map(pack, PackDto.class);
	}

	private Pack convertToEntity(PackDto packDto) {
		return modelMapper.map(packDto, Pack.class);
	}
}
