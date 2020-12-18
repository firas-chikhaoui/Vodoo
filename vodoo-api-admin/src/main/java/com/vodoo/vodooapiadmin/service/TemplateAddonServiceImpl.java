package com.vodoo.vodooapiadmin.service;


import com.vodoo.vodooapiadmin.dto.Enum.EnumMessage;
import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
import com.vodoo.vodooapiadmin.repository.IAddonsRepository;
import com.vodoo.vodooapiadmin.repository.ITemplateAddonsRepository;
import com.vodoo.vodooapiadmin.repository.ITemplateRepository;
import com.vodoo.vodooapipersistance.model.Addons;
import com.vodoo.vodooapipersistance.model.Template;
import com.vodoo.vodooapipersistance.model.TemplateAddons;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vodoo.vodooapiadmin.exception.CreateException;
import com.vodoo.vodooapiadmin.exception.DeleteException;
import com.vodoo.vodooapiadmin.exception.GetException;
import com.vodoo.vodooapiadmin.exception.UpdateException;

@Service
@Transactional
@Primary
public class TemplateAddonServiceImpl implements ITemplateAddonsService {

    @Autowired
    private ITemplateAddonsRepository templateAddonsRepository;
    private static final String ENTITY_NAME = "TemplateAddons";
    @Autowired
    private ITemplateRepository templateRepository;
    @Autowired
    private IAddonsRepository addonsRepository;


    @Transactional(readOnly = true)
    public Page<TemplateAddons> findAll(Pageable pageable) {

        return templateAddonsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public TemplateAddons findOne(String id) throws GetException {

        Optional<TemplateAddons> templateAddons = templateAddonsRepository.findById(id);

        if(!templateAddons.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return templateAddons.get();
    }

    @Override
    public TemplateAddons add(TemplateAddons templateAddons) throws CreateException {


        return templateAddonsRepository.saveAndFlush(templateAddons);
    }

    @Override
    public TemplateAddons update(TemplateAddons templateAddons) throws UpdateException {

        if( templateAddons.getIdTemplateAddons() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return templateAddonsRepository.saveAndFlush(templateAddons);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!templateAddonsRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        templateAddonsRepository.deleteById(id);
        return !templateAddonsRepository.existsById(id);
    }

    @Override
    public ObjectResponse affectAddonsToTemp(List<String> idAddonss, String idTemplate) {
        if(idTemplate!=null) {
            if (idAddonss.size() > 0) {
                if (!idTemplate.isEmpty()) {
                    try {
                        Template template = templateRepository.findByIdTemplate(idTemplate);
                        if (template != null) {
                            templateAddonsRepository.deleteAllByTemplate(template);
                            List<TemplateAddons> Templateaddonss = new ArrayList<>();
                            idAddonss.forEach(idAddons->{
                                Addons addons = addonsRepository.findByIdAddon(idAddons);
                                if (addons != null) {
                                    TemplateAddons templateaddons = new TemplateAddons();
                                    templateaddons.setAddons(addons);
                                    templateaddons.setTemplate(template);
                                   /* if (idAddons.equals(idaddons)) {
                                        templateaddons.setFdefaut(1);
                                    } else {
                                        templateaddons.setFdefaut(0);
                                    }*/
                                    Templateaddonss.add(templateaddons);
                                }
                            });
                            if (idAddonss.size() == Templateaddonss.size()) {
                                    templateAddonsRepository.saveAll(Templateaddonss);
                                    return new ObjectResponse(EnumMessage.AFFECT_WITH_SUCCESS.code, EnumMessage.AFFECT_WITH_SUCCESS.label, template);

                            } else {
                                return new ObjectResponse(EnumMessage.ONE_OR_MANY_ENTREPRISE_NOT_EXIST.code, EnumMessage.ONE_OR_MANY_ENTREPRISE_NOT_EXIST.label, null);
                            }
                        } /*else {
                            return new ModuleResponse(EnumMessage.USER_NOT_EXIST.code, EnumMessage.USER_NOT_EXIST.label, null);

                        }*/
                    } catch (Exception e) {
                        return new ObjectResponse(EnumMessage.ERREUR_QUERY.code, e.getMessage(), null);
                    }
                } /*else {
                    return new ModuleResponse(EnumMessage.ID_EMPTY.code, EnumMessage.ID_EMPTY.label, null);
                }*/
            } /*else {
                return new ModuleResponse(EnumMessage.LIST_IDENTREPRISE_EMPTY.code, EnumMessage.LIST_IDENTREPRISE_EMPTY.label, null);

            }*/
        }/*else{
            return new ModuleResponse(EnumMessage.ID_ENTREPSIE_SEND_EMPTY.code, EnumMessage.ID_ENTREPSIE_SEND_EMPTY.label, null);
        }*/
        return null;
    }




}