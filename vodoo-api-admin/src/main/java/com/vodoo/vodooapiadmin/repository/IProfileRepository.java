package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Profile;

public interface IProfileRepository extends JpaRepository<Profile, String> {

    public Profile findByIdProfile (String idProfile);

}
