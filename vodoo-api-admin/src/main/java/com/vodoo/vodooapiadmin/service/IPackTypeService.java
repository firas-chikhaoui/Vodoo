package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.PackType;

public interface IPackTypeService {

    Page<PackType> findAll(Pageable pageable) throws GetException;

    PackType findOne(String id) throws GetException;

    PackType add(PackType packType) throws CreateException;

    PackType update(PackType packType) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}