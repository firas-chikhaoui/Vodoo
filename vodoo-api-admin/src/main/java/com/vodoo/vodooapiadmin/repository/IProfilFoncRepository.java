package com.vodoo.vodooapiadmin.repository;

import com.vodoo.vodooapipersistance.model.Fonction;
import com.vodoo.vodooapipersistance.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.ProfilFonc;

import java.util.List;

public interface IProfilFoncRepository extends JpaRepository<ProfilFonc, String>{

    public List<ProfilFonc> findAllByProfile(Profile profile);
    public void deleteAllByProfile(Profile profile);
    public List<ProfilFonc> findAllByFonction(Fonction fonction);

}

