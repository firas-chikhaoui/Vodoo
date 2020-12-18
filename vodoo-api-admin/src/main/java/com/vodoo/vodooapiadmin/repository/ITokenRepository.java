package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Token;

public interface ITokenRepository extends JpaRepository<Token, String>{

}

