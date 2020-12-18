package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.TypeUtilisateur;

public interface ITypeUtilisateurService {

    Page<TypeUtilisateur> findAll(Pageable pageable) throws GetException;

    TypeUtilisateur findOne(String id) throws GetException;

    TypeUtilisateur add(TypeUtilisateur typeUtilisateur) throws CreateException;

    TypeUtilisateur update(TypeUtilisateur typeUtilisateur) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}