package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Pack;

public interface IPackService {

    Page<Pack> findAll(Pageable pageable) throws GetException;

    Pack findOne(String id) throws GetException;

    Pack add(Pack pack) throws CreateException;

    Pack update(Pack pack) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}