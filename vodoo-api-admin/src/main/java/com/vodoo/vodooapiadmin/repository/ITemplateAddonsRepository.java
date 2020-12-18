package com.vodoo.vodooapiadmin.repository;

import com.vodoo.vodooapipersistance.model.Addons;
import com.vodoo.vodooapipersistance.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vodoo.vodooapipersistance.model.TemplateAddons;

import java.util.*;


public interface ITemplateAddonsRepository extends JpaRepository<TemplateAddons, String> {

    public List<TemplateAddons> findAllByTemplate(Template template);
    public void deleteAllByTemplate(Template template);
    public List<TemplateAddons> findAllByAddons(Addons addons);

}
