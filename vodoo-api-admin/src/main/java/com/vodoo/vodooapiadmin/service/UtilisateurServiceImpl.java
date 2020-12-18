package com.vodoo.vodooapiadmin.service;

import java.util.Date;
import java.util.Optional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import com.vodoo.vodooapiadmin.dto.Enum.Message;
import com.vodoo.vodooapiadmin.dto.LoginDto;
import com.vodoo.vodooapiadmin.dto.ResetPasswordDto;
import com.vodoo.vodooapiadmin.dto.SendmailDto;
import com.vodoo.vodooapiadmin.exception.*;
import com.vodoo.vodooapiadmin.repository.ITraceAccesRepository;
import com.vodoo.vodooapiadmin.util.CryptDecyp;
import com.vodoo.vodooapiadmin.util.WebUtil;
import com.vodoo.vodooapipersistance.model.TraceAcces;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.Crypt;
import com.vodoo.vodooapiadmin.dto.ModuleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vodoo.vodooapiadmin.repository.IUtilisateurRepository;
import com.vodoo.vodooapipersistance.model.Utilisateur;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@Primary
public class UtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private IUtilisateurRepository utilisateurRepository;
    private static final String ENTITY_NAME = "Utilisateur";

    @Autowired
    ITraceAccesRepository logAccessRepository;

    @Autowired
    IMailService iMailService;

    @Override
    public ModuleResponse login(String login, String password, HttpServletRequest request) {
        try {
            if (login.isEmpty() || password.isEmpty()) {
                return new ModuleResponse(Message.USER_OR_PASSWORD_EMPTY.code,
                        Message.USER_OR_PASSWORD_EMPTY.label, null);
            }
            TraceAcces logAccess = WebUtil.getClientIp(request);
            logAccess.setIdentifiantTrace(login);
            logAccess.setDateTrace(new Timestamp(new Date().getTime()));
            CryptDecyp c = new CryptDecyp();
            String pwdCrypt = c.encrypt(password);
            Utilisateur utilisateur = utilisateurRepository.findByLoginAndPwd(login, pwdCrypt);
            if (utilisateur != null ) {
                if(utilisateur.getIsactif()==0) {
                    return new ModuleResponse(Message.USER_IS_NOT_ACTIF.code, Message.USER_IS_NOT_ACTIF.label, utilisateur);
                }
                if(utilisateur.getIsdeleted()==1) {
                    return new ModuleResponse(Message.USER_NOT_EXIST.code, Message.USER_NOT_EXIST.label, null);
                }
                logAccess.setStatus("Succés");
                logAccessRepository.save(logAccess);
                return new ModuleResponse(Message.USER_LOGIN.code, Message.USER_LOGIN.label, utilisateur);
            } else {
                logAccess.setStatus("Echoué");
                logAccessRepository.save(logAccess);
                return new ModuleResponse(Message.USER_NOT_EXIST.code, Message.USER_NOT_EXIST.label, null);
            }
        } catch (Exception e) {
            return new ModuleResponse(Message.USER_NOT_EXIST.code, Message.USER_NOT_EXIST.label, e.getMessage());
        }
    }


    @Transactional(readOnly = true)
    public Page<Utilisateur> findAll(Pageable pageable) {

        return utilisateurRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Utilisateur findOne(String id) throws GetException {

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        if(!utilisateur.isPresent()){

            GetException getException = new GetException();
            getException.setNomEntitie(ENTITY_NAME);
            getException.setRaisonExceptionNotFound();
            throw  getException;

        }

        return utilisateur.get();
    }

    @Override
    public ModuleResponse createUtilisateur(Utilisateur utilisateur, HttpServletRequest httpServletRequest, String id) {

        Utilisateur createdBy = utilisateurRepository.findByidUtilisateur(id);
        try {
            if (utilisateur == null) {
                return new ModuleResponse(Message.USER_EMPTY_FIELD.code, Message.USER_EMPTY_FIELD.label, null);
            }
            if (utilisateurRepository.findByLoginAndIsdeleted(utilisateur.getLogin(), 0) != null) {
                return new ModuleResponse(Message.LOGIN_EXIST.code, Message.LOGIN_EXIST.label, utilisateur.getLogin());
            }

            SendmailDto sendmailDto = new SendmailDto();
            if (utilisateur.getEmail() != null && utilisateur.getEmail().length() != 0 && utilisateur.getEmail().contains("@")) {
                sendmailDto.setText(
                        "<html><p><img src='https://le-rim.org/wp-content/uploads/2018/06/bienvenue-rim-730x482.png' alt='' width='633' height='281' /></p><h2 style='text-align: center;'>On vous souhaite la bienvenue au sein de notre plateforme <span style='color: #0000ff;'>Vodoo</span>!</h2><p>&nbsp;</p><h2 style='text-align: center;'><span style='text-align: center; color: #0000ff;'>&nbsp; &nbsp; &nbsp; <span style='color: #999999;'>votre compte est maintenant Actif.</span></span></h2><p>&nbsp;</p><h3>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span style='text-decoration: underline;'>Votre Login</span>: "
                                + utilisateur.getLogin()
                                + "</h3><h3>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span style='text-decoration: underline;'>Votre Mot de passe</span>:"
                                + utilisateur.getPwd()
                                + "</h3><p>&nbsp;</p><h2 style='text-align: center;'><span style='background-color: #3366ff;'><a style='background-color: #3366ff;' href='http://54.37.212.235:4200/auth/login'>&nbsp; <span style='color: #ffffff;'>Cliquer ici&nbsp;&nbsp;</span>&nbsp;</a></span></h2><p>&nbsp;</p><p><span style='color: #999999;'>&nbsp; &nbsp;Merci pour votre confiance!</span></p></html>");
                sendmailDto.setSendto(utilisateur.getEmail());
                sendmailDto.setSubject("Equipe Vodoo : Activiation du compte");
                iMailService.sendSimpleMessage(sendmailDto);
            }

            CryptDecyp c = new CryptDecyp();
            String pwdCrypt = c.encrypt(utilisateur.getPwd());
            utilisateur.setPwd(pwdCrypt);
//            utilisateur.setUtilisateur(createdBy);
            utilisateurRepository.save(utilisateur);
            return new ModuleResponse(Message.USER_CREATED.code, Message.USER_CREATED.label, utilisateur);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModuleResponse(Message.USER_NOT_Created.code, Message.USER_NOT_Created.label, null);
        }
    }


    @Override
    public ModuleResponse EditPassword(ResetPasswordDto resetPasswordDto) {
        try {
            if (resetPasswordDto != null) {
                if (resetPasswordDto.getIduser() != null && resetPasswordDto.getPassword() != null) {
                    Utilisateur utilisateur = utilisateurRepository.findByidUtilisateur(resetPasswordDto.getIduser());
                    if (utilisateur != null) {
                        CryptDecyp c = new CryptDecyp();
                        String pwdCrypt = c.encrypt(resetPasswordDto.getPassword());
                        utilisateur.setPwd(pwdCrypt);
                        utilisateurRepository.save(utilisateur);
                        return new ModuleResponse(Message.UPDATE_SUCESS.code, Message.UPDATE_SUCESS.label,
                                utilisateur);
                    } else {
                        return new ModuleResponse(Message.USER_NOT_EXIST.code, Message.USER_NOT_EXIST.label,
                                null);
                    }
                } else {
                    return new ModuleResponse(Message.SOME_INPUT_EMPTY.code, Message.SOME_INPUT_EMPTY.label,
                            null);
                }
            } else {
                return new ModuleResponse(Message.INPUT_EMPTY2.code, Message.INPUT_EMPTY2.label, null);
            }
        } catch (Exception e) {
            return new ModuleResponse(Message.ERREUR_QUERY.code, e.getMessage(), null);
        }
    }


    @Override
    public Utilisateur update(Utilisateur utilisateur) throws UpdateException {

        if( utilisateur.getIdUtilisateur() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        if(utilisateurRepository.existsByLoginAndIdUtilisateurNot(utilisateur.getLogin(), utilisateur.getIdUtilisateur())){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionChampUnique("codeUser");

            throw updateException;

        }

        return utilisateurRepository.saveAndFlush(utilisateur);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!utilisateurRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        utilisateurRepository.deleteById(id);
        return !utilisateurRepository.existsById(id);
    }

}