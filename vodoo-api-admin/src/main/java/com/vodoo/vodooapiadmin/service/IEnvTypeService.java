package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.EnvType;

public interface IEnvTypeService {

    Page<EnvType> findAll(Pageable pageable) throws GetException;

    EnvType findOne(String id) throws GetException;

    EnvType add(EnvType envType) throws CreateException;

    EnvType update(EnvType envType) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}