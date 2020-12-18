package com.vodoo.vodooapiadmin.endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.vodoo.vodooapiadmin.dto.*;
import com.vodoo.vodooapiadmin.dto.Enum.Message;
import com.vodoo.vodooapiadmin.endpoint.error.CustomException;
import com.vodoo.vodooapiadmin.service.IMailService;
import com.vodoo.vodooapiadmin.service.UtilisateurServiceImpl;
import io.swagger.annotations.*;
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

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapiadmin.service.IUtilisateurService;
import com.vodoo.vodooapipersistance.model.Utilisateur;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class UtilisateurEndpoint extends BaseEndpoint {

    static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IMailService emailService;


    Logger logger = LoggerFactory.getLogger(UtilisateurEndpoint.class);

    @GetMapping(path = "/v1/utilisateurs")
    @ApiOperation(
        value = "Get all utilisateurs",
        notes = "Returns first N utilisateurs specified by the size parameter with page offset specified by page parameter.",
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
                List<UtilisateurDto> utilisateursDto =
                utilisateurService.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
                    return ResponseEntity.ok().body(utilisateursDto);
            } catch (GetException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
            }
    }

    @GetMapping(path = "/v1/utilisateur/{id}")
    @ApiOperation(
        value = "Get utilisateur by id",
        notes = "Returns utilisateur for id specified.",
        response = UtilisateurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Utilisateur not found")})
    public ResponseEntity get(@ApiParam("Utilisateur id") @PathVariable("id") String id) {

        UtilisateurDto utilisateurDto = null;
        try {
            utilisateurDto = convertToDto(utilisateurService.findOne(id));
        } catch (GetException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(utilisateurDto);
    }


    @PostMapping("/v1/createUser")
    @ApiOperation(value = "création d'un utilisateur", notes = "un utilisateur crée"
            + "\n<b>result = 1 :</b> Utilisateur crée</b> \n"
            + "\n<b>result = -2 :</b> champs  est vide\n"
            +"\n<br>result = -1 </br> ustilisateur non crée"
            + "\n<b>result = 402 :</b> TOKEN MISSING.", authorizations = {
            @Authorization(value = "Bearer") }, response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "not found") })
    public Object createUtilisateur(@Valid @RequestBody Utilisateur utilisateur , HttpServletRequest httpServletRequest , String idAdmin )

    {
        try {
            return utilisateurService.createUtilisateur(utilisateur , httpServletRequest , idAdmin );
        }catch (Exception e) {
            logger.error("UserEndPoint.createUserEl Exception: " + e.getCause() + " " + e.getMessage());
            return new ModuleResponse(Message.USER_NOT_Created.code, e.getMessage(), null);
        }
    }

    @PostMapping("/v1/Authentification")
    @ApiOperation(value = "authentification", notes = "authentification "
            + "\n<b>result = 1 :</b> les information de login</b> \n"
            + "\n<b>result = -3 :</b>invalide mot de passe ou login</b> \n"

            + "\n<b>result = 402 :</b> TOKEN MISSING.", authorizations = {
            @Authorization(value = "Bearer") }, response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "not found") })
    public Object login(@Valid @RequestBody LoginDto dtoLogin, HttpServletRequest request)
    {
        try
        {
            return utilisateurService.login(dtoLogin.getLogin(), dtoLogin.getPwd(),request);
        }
        catch (Exception e) {
            logger.error("UserEndPoint.login Exception: " + e.getCause() + " " + e.getMessage());
            return new ModuleResponse(Message.USER_OR_PASSWORD_EMPTY.code, e.getMessage(), null);
        }

    }

    @PostMapping("/v1/SendMail")
    @ApiOperation(value = " Affect Session to User", notes = "Affect Session to User.\n"
            + "\n<b>result = 1 :</b>list Session affected to user </b> \n" + "\n<b>result = 0 :</b>List Session empty\n"
            + "\n<b>result = -1 :</b> user is empty\n" + "\n<b>result = -2 :</b> list Session and user not affected\n"
            + "\n<b>result = 401 :</b> TOKEN NOT AUTHORIZED\n"
            + "\n<b>result = 402 :</b> TOKEN MISSING.", authorizations = {
            @Authorization(value = "Bearer") }, response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "not found") })
    public Object SendMail(HttpServletRequest request,@RequestBody @Valid SendmailDto sendmailDto) {
        logger.info("UserController SendMail :" );
        // TokenValidationOutput res =
        // tokenValidationOutputService.getValidate(request);
        try {
            // if(res.getResult()== ApiConstants.TOKEN_VALID) {
            // logger.info("UserController.GetUserById : TOKEN VALID" );
            ModuleResponse user = emailService.sendSimpleMessage(sendmailDto);
            return  user;
            // } else {
            // logger.info("UserController.GetUserById : TOKEN MISSING");
            // return res;
            // }
        } catch (Exception e) {
            logger.error("UserController.SendMail Exception: " + e.getCause() + " " + e.getMessage());
            return new CustomException(e.getMessage(), e.getCause());
        }

    }

    @PostMapping("/v1/EditPassword")
    @ApiOperation(value = "Modifier le mot de passe par default", notes = "Retourner l'utilisateur avec son nouveau mot de passe.\n"
            + "\n<b>result = 1 :</b> Object est modifiÃ©e avec succÃ©e</b> \n"
            + "\n<b>result = 0 :</b> Utilisateur n'existe pase\n"
            + "\n<b>result = -1 :</b>l'object envoyer est null\n"
            + "\n<b>result = -2 :</b> un ou plusieur attribut envoyer sont null\n"
            + "\n<b>result = -3 :</b> Query failed\n"
            + "\n<b>result = 401 :</b> TOKEN NOT AUTHORIZED\n"
            + "\n<b>result = 402 :</b> TOKEN MISSING.", authorizations = {
            @Authorization(value = "Bearer") }, response = Object.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "not found") })
    public Object EditPassword(HttpServletRequest request,@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        logger.info("UtilisateurEndPoint EditPassword Start");

        try {
            ModuleResponse response = utilisateurService.EditPassword(resetPasswordDto);
            return response;
            // } else {
            // logger.info("UserEndpoint.getListUser TOKEN MISSING");
            // return res;
            // }
        } catch (Exception e) {
            logger.error("UserEndpoint.getListUser Exception: " + e.getCause() + " " + e.getMessage());
            return new CustomException(e.getMessage(), e.getCause());
        }

    }



    @PutMapping(path = "/v1/utilisateur",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
        value = "Update existing utilisateur",
        notes = "Updates exisitng utilisateur. Returns updated utilisateur.",
        response = UtilisateurDto.class)
    public ResponseEntity update(
        @ApiParam("Utilisateur") @Valid @RequestBody UtilisateurDto utilisateurDto){

        try {
            utilisateurDto = convertToDto(utilisateurService.update(convertToEntity(utilisateurDto)));
        } catch (UpdateException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(utilisateurDto);
    }

    @DeleteMapping(path = "/v1/utilisateur/{id}")
    @ApiOperation(
        value = "Delete utilisateur",
        notes = "Delete utilisateur. Returns Boolean.",
        response = Boolean.class)
    public ResponseEntity delete(@ApiParam("utilisateur id") @PathVariable("id") String id){

        boolean result;

        try {
            result = utilisateurService.delete(id);
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
        }

        return ResponseEntity.ok().body(Collections.singletonMap("result", result));
    }


    private UtilisateurDto convertToDto(Utilisateur utilisateur) {
        return modelMapper.map(utilisateur, UtilisateurDto.class);
    }

    private Utilisateur convertToEntity(UtilisateurDto utilisateurDto) {
        return modelMapper.map(utilisateurDto, Utilisateur.class);
    }
}
