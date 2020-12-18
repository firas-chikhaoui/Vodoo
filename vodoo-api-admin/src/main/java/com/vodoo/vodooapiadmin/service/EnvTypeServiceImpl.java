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
import com.vodoo.vodooapiadmin.repository.IEnvTypeRepository;
import com.vodoo.vodooapipersistance.model.EnvType;

@Service
@Transactional
@Primary
public class EnvTypeServiceImpl implements IEnvTypeService {

    @Autowired
    private IEnvTypeRepository envTypeRepository;
    private static final String ENTITY_NAME = "EnvType";

    @Transactional(readOnly = true)
    public Page<EnvType> findAll(Pageable pageable) {

        return envTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public EnvType findOne(String id) throws GetException {

        Optional<EnvType> envType = envTypeRepository.findById(id);

        if(!envType.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return envType.get();
    }

    @Override
    public EnvType add(EnvType envType) throws CreateException {


        return envTypeRepository.saveAndFlush(envType);
    }

    @Override
    public EnvType update(EnvType envType) throws UpdateException {

        if( envType.getIdEnvtype() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return envTypeRepository.saveAndFlush(envType);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!envTypeRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        envTypeRepository.deleteById(id);
        return !envTypeRepository.existsById(id);
    }

}