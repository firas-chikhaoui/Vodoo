package com.vodoo.vodooapiadmin.service;

import com.vodoo.vodooapiadmin.dto.LoginDto;
import com.vodoo.vodooapiadmin.dto.ModuleResponse;
import com.vodoo.vodooapiadmin.dto.ResetPasswordDto;
import com.vodoo.vodooapiadmin.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapipersistance.model.Utilisateur;

import javax.servlet.http.HttpServletRequest;

public interface IUtilisateurService {

    public ModuleResponse EditPassword(ResetPasswordDto resetPasswordDto);

    public ModuleResponse login(String login, String password, HttpServletRequest request);

    Page<Utilisateur> findAll(Pageable pageable) throws GetException;

    Utilisateur findOne(String id) throws GetException;

    //Utilisateur add(Utilisateur utilisateur) throws CreateException;

    public ModuleResponse createUtilisateur(Utilisateur utilisateur, HttpServletRequest httpServletRequest, String id);

    Utilisateur update(Utilisateur utilisateur) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}