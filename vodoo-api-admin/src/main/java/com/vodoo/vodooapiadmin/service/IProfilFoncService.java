package com.vodoo.vodooapiadmin.service;

import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.ProfilFonc;

import java.util.List;

public interface IProfilFoncService {

    Page<ProfilFonc> findAll(Pageable pageable) throws GetException;

    ProfilFonc findOne(String id) throws GetException;

    ProfilFonc add(ProfilFonc profilFonc) throws CreateException;

    ProfilFonc update(ProfilFonc profilFonc) throws UpdateException;

    boolean delete(String id) throws DeleteException;

    public ObjectResponse affectFonctionsToProfile(List<String> idFonctions, String idProfile);


}