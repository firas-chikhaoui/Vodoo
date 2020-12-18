package com.vodoo.vodooapiadmin.service;

import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;
import com.vodoo.vodooapipersistance.model.TemplateAddons;

import java.util.List;


public interface ITemplateAddonsService {

    Page<TemplateAddons> findAll(Pageable pageable) throws GetException;

    TemplateAddons findOne(String id) throws GetException;

    TemplateAddons add(TemplateAddons templateAddons) throws CreateException;

    TemplateAddons update(TemplateAddons templateAddons) throws UpdateException;

    boolean delete(String id) throws DeleteException;

    public ObjectResponse affectAddonsToTemp(List<String> idAddonss, String idTemplate);


}