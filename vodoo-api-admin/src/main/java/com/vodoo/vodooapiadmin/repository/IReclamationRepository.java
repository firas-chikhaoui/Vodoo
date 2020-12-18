package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Reclamation;

public interface IReclamationRepository extends JpaRepository<Reclamation, String> {

}
