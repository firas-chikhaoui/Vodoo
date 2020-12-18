package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Secteur;

public interface ISecteurService {

    Page<Secteur> findAll(Pageable pageable) throws GetException;

    Secteur findOne(String id) throws GetException;

    Secteur add(Secteur secteur) throws CreateException;

    Secteur update(Secteur secteur) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}