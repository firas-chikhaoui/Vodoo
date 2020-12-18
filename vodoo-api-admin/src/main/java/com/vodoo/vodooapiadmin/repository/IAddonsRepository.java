package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Addons;


public interface IAddonsRepository extends JpaRepository<Addons, String>{
    public Addons findByIdAddon(String id);
}

