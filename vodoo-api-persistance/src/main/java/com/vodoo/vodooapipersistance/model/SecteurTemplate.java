package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the secteur_template database table.
 * 
 */
@Entity
@Table(name="secteur_template")
@NamedQuery(name="SecteurTemplate.findAll", query="SELECT s FROM SecteurTemplate s")
public class SecteurTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_secteur_template")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idSecteurTemplate;

	//bi-directional many-to-one association to Secteur
	@ManyToOne
	@JoinColumn(name="id_secteur")
	private Secteur secteur;

	//bi-directional many-to-one association to Template
	@ManyToOne
	@JoinColumn(name="id_template")
	private Template template;

	public SecteurTemplate() {
	}

	public String getIdSecteurTemplate() {
		return this.idSecteurTemplate;
	}

	public void setIdSecteurTemplate(String idSecteurTemplate) {
		this.idSecteurTemplate = idSecteurTemplate;
	}

	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}