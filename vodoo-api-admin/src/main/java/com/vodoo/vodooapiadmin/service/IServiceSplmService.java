package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.ServiceSplm;

public interface IServiceSplmService {

	Page<ServiceSplm> findAll(Pageable pageable) throws GetException;

	ServiceSplm findOne(String id) throws GetException;

	ServiceSplm add(ServiceSplm serviceSplm) throws CreateException;

	ServiceSplm update(ServiceSplm serviceSplm) throws UpdateException;

	boolean delete(String id) throws DeleteException;

}