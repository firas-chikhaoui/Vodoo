package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Addons;


public interface IAddonsService {

    Page<Addons> findAll(Pageable pageable) throws GetException;

    Addons findOne(String id) throws GetException;

    Addons add(Addons addons) throws CreateException;

    Addons update(Addons addons) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}