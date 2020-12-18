package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Client;


public interface IClientRepository extends JpaRepository<Client, String>{

}

