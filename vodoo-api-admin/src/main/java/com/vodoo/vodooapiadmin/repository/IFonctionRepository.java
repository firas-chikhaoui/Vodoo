package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Fonction;

import java.util.List;

public interface IFonctionRepository extends JpaRepository<Fonction, String>{

    boolean existsByCodeFonc(String codeFonc);

    boolean existsByCodeFoncAndIdFoncNot(String codeFonc, String idFonc);

    List<Fonction> findAllByProfilFoncsProfileUserProfilsUtilisateurIdUtilisateurOrderByFonctionDesc(String iduser);

    public Fonction findByIdFonc(String id);

}

