package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.UserProfil;

public interface IUserProfilRepository extends JpaRepository<UserProfil, String> {

}
