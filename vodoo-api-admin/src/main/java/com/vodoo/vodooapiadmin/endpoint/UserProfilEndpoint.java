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

import com.vodoo.vodooapiadmin.dto.UserProfilDto;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IUserProfilService;
import com.vodoo.vodooapipersistance.model.UserProfil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated // required for @Valid on method parameters such as @RequesParam, @PathVariable,
			// @RequestHeader
public class UserProfilEndpoint extends BaseEndpoint {

	static final int DEFAULT_PAGE_SIZE = 10;

	@Autowired
	private IUserProfilService userProfilService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(path = "/v1/userProfils")
	@ApiOperation(value = "Get all userProfils", notes = "Returns first N userProfils specified by the size parameter with page offset specified by page parameter.", response = Collection.class)
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
			List<UserProfilDto> userProfilsDto = userProfilService.findAll(pageable).stream().map(this::convertToDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok().body(userProfilsDto);
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}
	}

	@GetMapping(path = "/v1/userProfil/{id}")
	@ApiOperation(value = "Get userProfil by id", notes = "Returns userProfil for id specified.", response = UserProfilDto.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "UserProfil not found") })
	public ResponseEntity get(@ApiParam("UserProfil id") @PathVariable("id") String id) {

		UserProfilDto userProfilDto = null;
		try {
			userProfilDto = convertToDto(userProfilService.findOne(id));
		} catch (GetException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(userProfilDto);
	}

	@PostMapping(path = "/v1/userProfil", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create new userProfil", notes = "Creates new userProfil. Returns created userProfil with id.", response = UserProfilDto.class)
	public ResponseEntity add(@ApiParam("UserProfil") @Valid @RequestBody UserProfilDto userProfilDto) {

		try {
			userProfilDto = convertToDto(userProfilService.add(convertToEntity(userProfilDto)));
		} catch (CreateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(userProfilDto);
	}

	@PutMapping(path = "/v1/userProfil", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update existing userProfil", notes = "Updates exisitng userProfil. Returns updated userProfil.", response = UserProfilDto.class)
	public ResponseEntity update(@ApiParam("UserProfil") @Valid @RequestBody UserProfilDto userProfilDto) {

		try {
			userProfilDto = convertToDto(userProfilService.update(convertToEntity(userProfilDto)));
		} catch (UpdateException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(userProfilDto);
	}

	@DeleteMapping(path = "/v1/userProfil/{id}")
	@ApiOperation(value = "Delete userProfil", notes = "Delete userProfil. Returns Boolean.", response = Boolean.class)
	public ResponseEntity delete(@ApiParam("userProfil id") @PathVariable("id") String id) {

		boolean result;

		try {
			result = userProfilService.delete(id);
		} catch (DeleteException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}

		return ResponseEntity.ok().body(Collections.singletonMap("result", result));
	}

	private UserProfilDto convertToDto(UserProfil userProfil) {
		return modelMapper.map(userProfil, UserProfilDto.class);
	}

	private UserProfil convertToEntity(UserProfilDto userProfilDto) {
		return modelMapper.map(userProfilDto, UserProfil.class);
	}
}
