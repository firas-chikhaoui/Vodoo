package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.Template;

public interface ITemplateService {

    Page<Template> findAll(Pageable pageable) throws GetException;

    Template findOne(String id) throws GetException;

    Template add(Template template) throws CreateException;

    Template update(Template template) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}