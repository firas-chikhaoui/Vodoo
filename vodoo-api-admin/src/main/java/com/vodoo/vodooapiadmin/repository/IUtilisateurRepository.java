package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, String>{
    boolean existsByLogin(String login);

    boolean existsByIdUtilisateur (String idUser);

    boolean existsByLoginAndIdUtilisateurNot(String codeUser, String idUtilisateur);


    public Utilisateur findByLoginAndPwd(String login,String pwd);

    public Utilisateur findByidUtilisateur(String idUtilisateur);

    public Utilisateur findByLoginAndIsdeleted(String login, Integer isdeleted);

}

