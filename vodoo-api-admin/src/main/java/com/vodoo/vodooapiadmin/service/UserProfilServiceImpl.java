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
import com.vodoo.vodooapiadmin.repository.IUserProfilRepository;
import com.vodoo.vodooapipersistance.model.UserProfil;

@Service
@Transactional
@Primary
public class UserProfilServiceImpl implements IUserProfilService {

    @Autowired
    private IUserProfilRepository userProfilRepository;
    private static final String ENTITY_NAME = "UserProfil";

    @Transactional(readOnly = true)
    public Page<UserProfil> findAll(Pageable pageable) {

        return userProfilRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public UserProfil findOne(String id) throws GetException {

        Optional<UserProfil> userProfil = userProfilRepository.findById(id);

        if(!userProfil.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return userProfil.get();
    }

    @Override
    public UserProfil add(UserProfil userProfil) throws CreateException {


        return userProfilRepository.saveAndFlush(userProfil);
    }

    @Override
    public UserProfil update(UserProfil userProfil) throws UpdateException {

        if( userProfil.getIdUserProfil() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return userProfilRepository.saveAndFlush(userProfil);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!userProfilRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        userProfilRepository.deleteById(id);
        return !userProfilRepository.existsById(id);
    }

}