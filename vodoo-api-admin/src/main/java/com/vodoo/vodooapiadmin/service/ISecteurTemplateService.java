package com.vodoo.vodooapiadmin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.SecteurTemplate;

public interface ISecteurTemplateService {

    Page<SecteurTemplate> findAll(Pageable pageable) throws GetException;

    SecteurTemplate findOne(String id) throws GetException;

    SecteurTemplate add(SecteurTemplate secteurTemplate) throws CreateException;

    SecteurTemplate update(SecteurTemplate secteurTemplate) throws UpdateException;

    boolean delete(String id) throws DeleteException;


}