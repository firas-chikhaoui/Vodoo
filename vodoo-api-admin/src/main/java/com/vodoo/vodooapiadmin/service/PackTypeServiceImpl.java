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
import com.vodoo.vodooapiadmin.repository.IPackTypeRepository;
import com.vodoo.vodooapipersistance.model.PackType;

@Service
@Transactional
@Primary
public class PackTypeServiceImpl implements IPackTypeService {

    @Autowired
    private IPackTypeRepository packTypeRepository;
    private static final String ENTITY_NAME = "PackType";

    @Transactional(readOnly = true)
    public Page<PackType> findAll(Pageable pageable) {

        return packTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PackType findOne(String id) throws GetException {

        Optional<PackType> packType = packTypeRepository.findById(id);

        if(!packType.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return packType.get();
    }

    @Override
    public PackType add(PackType packType) throws CreateException {


        return packTypeRepository.saveAndFlush(packType);
    }

    @Override
    public PackType update(PackType packType) throws UpdateException {

        if( packType.getIdPacktype() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return packTypeRepository.saveAndFlush(packType);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!packTypeRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        packTypeRepository.deleteById(id);
        return !packTypeRepository.existsById(id);
    }

}