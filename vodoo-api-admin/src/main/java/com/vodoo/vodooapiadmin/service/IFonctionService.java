package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Fonction;

import java.util.List;

public interface IFonctionService {

    Page<Fonction> findAll(Pageable pageable) throws GetException;

    Fonction findOne(String id) throws GetException;

    Fonction add(Fonction fonction) throws CreateException;

    Fonction update(Fonction fonction) throws UpdateException;

    boolean delete(String id) throws DeleteException;

    List<Fonction> getFonctionsByUser(String id) throws GetException;


}