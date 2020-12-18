package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the template_addons database table.
 * 
 */
@Entity
@Table(name="template_addons")
@NamedQuery(name="TemplateAddon.findAll", query="SELECT t FROM TemplateAddons t")
public class TemplateAddons implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_template_addons")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idTemplateAddons;

	//bi-directional many-to-one association to Addon
	@ManyToOne
	@JoinColumn(name="id_addon")
	private Addons addons;

	//bi-directional many-to-one association to Template
	@ManyToOne
	@JoinColumn(name="id_template")
	private Template template;

	public TemplateAddons() {
	}

	public String getIdTemplateAddons() {
		return this.idTemplateAddons;
	}

	public void setIdTemplateAddons(String idTemplateAddons) {
		this.idTemplateAddons = idTemplateAddons;
	}

	public Addons getAddons() {
		return this.addons;
	}

	public void setAddons(Addons addons) {
		this.addons = addons;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}