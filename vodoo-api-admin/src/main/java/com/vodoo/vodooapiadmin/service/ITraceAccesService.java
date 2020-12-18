package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.TraceAcces;

public interface ITraceAccesService {

    Page<TraceAcces> findAll(Pageable pageable) throws GetException;

    TraceAcces findOne(String id) throws GetException;

    TraceAcces add(TraceAcces traceAcces) throws CreateException;

    TraceAcces update(TraceAcces traceAcces) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}