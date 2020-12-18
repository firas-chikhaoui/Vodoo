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
import com.vodoo.vodooapiadmin.repository.ITypeUtilisateurRepository;
import com.vodoo.vodooapipersistance.model.TypeUtilisateur;

@Service
@Transactional
@Primary
public class TypeUtilisateurServiceImpl implements ITypeUtilisateurService {

    @Autowired
    private ITypeUtilisateurRepository typeUtilisateurRepository;
    private static final String ENTITY_NAME = "TypeUtilisateur";

    @Transactional(readOnly = true)
    public Page<TypeUtilisateur> findAll(Pageable pageable) {

        return typeUtilisateurRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public TypeUtilisateur findOne(String id) throws GetException {

        Optional<TypeUtilisateur> typeUtilisateur = typeUtilisateurRepository.findById(id);

        if(!typeUtilisateur.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return typeUtilisateur.get();
    }

    @Override
    public TypeUtilisateur add(TypeUtilisateur typeUtilisateur) throws CreateException {


        return typeUtilisateurRepository.saveAndFlush(typeUtilisateur);
    }

    @Override
    public TypeUtilisateur update(TypeUtilisateur typeUtilisateur) throws UpdateException {

        if( typeUtilisateur.getIdTypeUtil() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return typeUtilisateurRepository.saveAndFlush(typeUtilisateur);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!typeUtilisateurRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        typeUtilisateurRepository.deleteById(id);
        return !typeUtilisateurRepository.existsById(id);
    }

}