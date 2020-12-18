package com.vodoo.vodooapiadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vodoo.vodooapiadmin.dto.Enum.EnumMessage;
import com.vodoo.vodooapiadmin.dto.response.ObjectResponse;
import com.vodoo.vodooapiadmin.repository.IFonctionRepository;
import com.vodoo.vodooapiadmin.repository.IProfileRepository;
import com.vodoo.vodooapipersistance.model.Fonction;
import com.vodoo.vodooapipersistance.model.Profile;
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
import com.vodoo.vodooapiadmin.repository.IProfilFoncRepository;
import com.vodoo.vodooapipersistance.model.ProfilFonc;

@Service
@Transactional
@Primary
public class ProfilFoncServiceImpl implements IProfilFoncService {

    @Autowired
    private IProfilFoncRepository profilFoncRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Autowired
    private IFonctionRepository fonctionsRepository;
    private static final String ENTITY_NAME = "ProfilFonc";

    @Transactional(readOnly = true)
    public Page<ProfilFonc> findAll(Pageable pageable) {

        return profilFoncRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProfilFonc findOne(String id) throws GetException {

        Optional<ProfilFonc> profilFonc = profilFoncRepository.findById(id);

        if(!profilFonc.isPresent()){

                GetException getException = new GetException();
                getException.setNomEntitie(ENTITY_NAME);
                getException.setRaisonExceptionNotFound();
                throw  getException;

        }

        return profilFonc.get();
    }

    @Override
    public ProfilFonc add(ProfilFonc profilFonc) throws CreateException {


        return profilFoncRepository.saveAndFlush(profilFonc);
    }

    @Override
    public ProfilFonc update(ProfilFonc profilFonc) throws UpdateException {

        if( profilFonc.getIdProfilFonc() == null){

            UpdateException updateException = new UpdateException();
            updateException.setNomEntitie(ENTITY_NAME);
            updateException.setRaisonExceptionIdNull();

            throw updateException;

        }

        return profilFoncRepository.saveAndFlush(profilFonc);
    }

    @Override
    public boolean delete(String id) throws DeleteException {

        if(!profilFoncRepository.existsById(id)){
            DeleteException deleteException = new DeleteException();
            deleteException.setNomEntitie(ENTITY_NAME);
            deleteException.setRaisonDosentExist();

            throw deleteException;
        }

        profilFoncRepository.deleteById(id);
        return !profilFoncRepository.existsById(id);
    }
    @Override
    public ObjectResponse affectFonctionsToProfile(List<String> idFonctions, String idProfile) {
        if(idProfile!=null) {
            if (idFonctions.size() > 0) {
                if (!idProfile.isEmpty()) {
                    try {
                        Profile profile = profileRepository.findByIdProfile(idProfile);
                        if (profile != null) {
                            profilFoncRepository.deleteAllByProfile(profile);
                            List<ProfilFonc> Profilefonctions = new ArrayList<>();
                            idFonctions.forEach(idFonction->{
                                Fonction fonctions = fonctionsRepository.findByIdFonc(idFonction);
                                if (fonctions != null) {
                                    ProfilFonc profilefonctions = new ProfilFonc();
                                    profilefonctions.setFonction(fonctions);
                                    profilefonctions.setProfile(profile);
                                   /* if (idFonction.equals(idfonctions)) {
                                        profilefonctions.setFdefaut(1);
                                    } else {
                                        profilefonctions.setFdefaut(0);
                                    }*/
                                    Profilefonctions.add(profilefonctions);
                                }
                            });
                            if (idFonctions.size() == Profilefonctions.size()) {
                                profilFoncRepository.saveAll(Profilefonctions);
                                return new ObjectResponse(EnumMessage.AFFECT_WITH_SUCCESS.code, EnumMessage.AFFECT_WITH_SUCCESS.label, profile);

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