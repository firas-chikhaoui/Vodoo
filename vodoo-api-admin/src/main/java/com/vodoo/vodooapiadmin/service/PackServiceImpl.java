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
import com.vodoo.vodooapiadmin.repository.IPackRepository;
import com.vodoo.vodooapipersistance.model.Pack;

@Service
@Transactional
@Primary
public class PackServiceImpl implements IPackService {

    @Autowired
    private IPackRepository packRepository;
    private static final String ENTITY_NAME = "Pack";

    @Transactional(readOnly = true)
    public Page<Pack> findAll(Pageable pageable) {

        return packRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Pack findOne(String id) throws GetException {

        Optional<Pack> pack = packRepository.findById(id);

        if(!pack.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return pack.get();
    }

    @Override
    public Pack add(Pack pack) throws CreateException {


        return packRepository.saveAndFlush(pack);
    }

    @Override
    public Pack update(Pack pack) throws UpdateException {

        if( pack.getIdPack() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return packRepository.saveAndFlush(pack);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!packRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        packRepository.deleteById(id);
        return !packRepository.existsById(id);
    }

}