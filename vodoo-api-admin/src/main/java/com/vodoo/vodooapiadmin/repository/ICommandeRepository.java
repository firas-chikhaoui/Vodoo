package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Commande;


public interface ICommandeRepository extends JpaRepository<Commande, String>{

    boolean existsByCodeCommande(String codeCommande);

    boolean existsByCodeCommandeAndIdCommandeNot(String codeCommande, String idCommande);

}

