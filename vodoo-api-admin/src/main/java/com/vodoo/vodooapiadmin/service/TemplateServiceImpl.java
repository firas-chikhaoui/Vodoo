package com.vodoo.vodooapiadmin.service;

import java.util.Optional;

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
import com.vodoo.vodooapiadmin.repository.ITemplateRepository;
import com.vodoo.vodooapipersistance.model.Template;

@Service
@Transactional
@Primary
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private ITemplateRepository templateRepository;
    private static final String ENTITY_NAME = "Template";

    @Transactional(readOnly = true)
    public Page<Template> findAll(Pageable pageable) {

        return templateRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Template findOne(String id) throws GetException {

        Optional<Template> template = templateRepository.findById(id);

        if(!template.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return template.get();
    }

    @Override
    public Template add(Template template) throws CreateException {


        return templateRepository.saveAndFlush(template);
    }

    @Override
    public Template update(Template template) throws UpdateException {

        if( template.getIdTemplate() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return templateRepository.saveAndFlush(template);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!templateRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        templateRepository.deleteById(id);
        return !templateRepository.existsById(id);
    }

}