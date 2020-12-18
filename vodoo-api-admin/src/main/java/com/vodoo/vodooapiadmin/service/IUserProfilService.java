package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.UserProfil;

public interface IUserProfilService {

	Page<UserProfil> findAll(Pageable pageable) throws GetException;

	UserProfil findOne(String id) throws GetException;

	UserProfil add(UserProfil userProfil) throws CreateException;

	UserProfil update(UserProfil userProfil) throws UpdateException;

	boolean delete(String id) throws DeleteException;

}