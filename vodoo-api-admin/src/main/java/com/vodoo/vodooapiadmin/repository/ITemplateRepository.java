package com.vodoo.vodooapiadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.Template;

public interface ITemplateRepository extends JpaRepository<Template, String>{
    public Template findByIdTemplate(String idTemplate);
}

