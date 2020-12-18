package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Pack;

public interface IPackRepository extends JpaRepository<Pack, String> {

}
