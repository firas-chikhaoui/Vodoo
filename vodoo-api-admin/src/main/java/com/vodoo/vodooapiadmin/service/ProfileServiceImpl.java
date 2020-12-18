package com.vodoo.vodooapiadmin.service;

import java.util.Date;
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
import com.vodoo.vodooapiadmin.repository.IProfileRepository;
import com.vodoo.vodooapipersistance.model.Profile;

@Service
@Transactional
@Primary
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private IProfileRepository profileRepository;
    private static final String ENTITY_NAME = "Profile";

    @Transactional(readOnly = true)
    public Page<Profile> findAll(Pageable pageable) {

        return profileRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Profile findOne(String id) throws GetException {

        Optional<Profile> profile = profileRepository.findById(id);

        if(!profile.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return profile.get();
    }

    @Override
    public Profile add(Profile profile) throws CreateException {
        profile.setIsdeleted(0);

            profile.setDateCreaProfi(new Date());
        return profileRepository.saveAndFlush(profile);
    }

    @Override
    public Profile update(Profile profile) throws UpdateException {

        if( profile.getIdProfile() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return profileRepository.saveAndFlush(profile);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!profileRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        profileRepository.deleteById(id);
        return !profileRepository.existsById(id);
    }

}