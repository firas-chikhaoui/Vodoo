package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Profile;

public interface IProfileService {

    Page<Profile> findAll(Pageable pageable) throws GetException;

    Profile findOne(String id) throws GetException;

    Profile add(Profile profile) throws CreateException;

    Profile update(Profile profile) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}