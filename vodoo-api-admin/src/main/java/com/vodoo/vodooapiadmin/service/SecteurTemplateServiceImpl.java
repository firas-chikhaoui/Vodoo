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
import com.vodoo.vodooapiadmin.repository.ISecteurTemplateRepository;
import com.vodoo.vodooapipersistance.model.SecteurTemplate;

@Service
@Transactional
@Primary
public class SecteurTemplateServiceImpl implements ISecteurTemplateService {

    @Autowired
    private ISecteurTemplateRepository secteurTemplateRepository;
    private static final String ENTITY_NAME = "SecteurTemplate";

    @Transactional(readOnly = true)
    public Page<SecteurTemplate> findAll(Pageable pageable) {

        return secteurTemplateRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public SecteurTemplate findOne(String id) throws GetException {

        Optional<SecteurTemplate> secteurTemplate = secteurTemplateRepository.findById(id);

        if(!secteurTemplate.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return secteurTemplate.get();
    }

    @Override
    public SecteurTemplate add(SecteurTemplate secteurTemplate) throws CreateException {


        return secteurTemplateRepository.saveAndFlush(secteurTemplate);
    }

    @Override
    public SecteurTemplate update(SecteurTemplate secteurTemplate) throws UpdateException {

        if( secteurTemplate.getIdSecteurTemplate() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return secteurTemplateRepository.saveAndFlush(secteurTemplate);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!secteurTemplateRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        secteurTemplateRepository.deleteById(id);
        return !secteurTemplateRepository.existsById(id);
    }

}