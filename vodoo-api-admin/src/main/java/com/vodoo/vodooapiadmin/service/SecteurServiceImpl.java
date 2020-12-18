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
import com.vodoo.vodooapiadmin.repository.ISecteurRepository;
import com.vodoo.vodooapipersistance.model.Secteur;

@Service
@Transactional
@Primary
public class SecteurServiceImpl implements ISecteurService {

    @Autowired
    private ISecteurRepository secteurRepository;
    private static final String ENTITY_NAME = "Secteur";

    @Transactional(readOnly = true)
    public Page<Secteur> findAll(Pageable pageable) {

        return secteurRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Secteur findOne(String id) throws GetException {

        Optional<Secteur> secteur = secteurRepository.findById(id);

        if(!secteur.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return secteur.get();
    }

    @Override
    public Secteur add(Secteur secteur) throws CreateException {


        return secteurRepository.saveAndFlush(secteur);
    }

    @Override
    public Secteur update(Secteur secteur) throws UpdateException {

        if( secteur.getIdSecteur() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return secteurRepository.saveAndFlush(secteur);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!secteurRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        secteurRepository.deleteById(id);
        return !secteurRepository.existsById(id);
    }

}