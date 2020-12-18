package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the template database table.
 * 
 */
@Entity
@NamedQuery(name="Template.findAll", query="SELECT t FROM Template t")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_template")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idTemplate;

	@Column(name="createur_template")
	private String createurTemplate;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_template")
	private Date dateCreaTemplate;

	@Column(name="designation_template")
	private String designationTemplate;

	private String filename;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="template")
	private List<Commande> commandes;

	//bi-directional many-to-one association to SecteurTemplate
	@OneToMany(mappedBy="template")
	private List<SecteurTemplate> secteurTemplates;

	//bi-directional many-to-one association to TemplateAddon
	@OneToMany(mappedBy="template")
	private List<TemplateAddons> templateAddons;

	public Template() {
	}

	public String getIdTemplate() {
		return this.idTemplate;
	}

	public void setIdTemplate(String idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getCreateurTemplate() {
		return this.createurTemplate;
	}

	public void setCreateurTemplate(String createurTemplate) {
		this.createurTemplate = createurTemplate;
	}

	public Date getDateCreaTemplate() {
		return this.dateCreaTemplate;
	}

	public void setDateCreaTemplate(Date dateCreaTemplate) {
		this.dateCreaTemplate = dateCreaTemplate;
	}

	public String getDesignationTemplate() {
		return this.designationTemplate;
	}

	public void setDesignationTemplate(String designationTemplate) {
		this.designationTemplate = designationTemplate;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setTemplate(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setTemplate(null);

		return commande;
	}

	public List<SecteurTemplate> getSecteurTemplates() {
		return this.secteurTemplates;
	}

	public void setSecteurTemplates(List<SecteurTemplate> secteurTemplates) {
		this.secteurTemplates = secteurTemplates;
	}

	public SecteurTemplate addSecteurTemplate(SecteurTemplate secteurTemplate) {
		getSecteurTemplates().add(secteurTemplate);
		secteurTemplate.setTemplate(this);

		return secteurTemplate;
	}

	public SecteurTemplate removeSecteurTemplate(SecteurTemplate secteurTemplate) {
		getSecteurTemplates().remove(secteurTemplate);
		secteurTemplate.setTemplate(null);

		return secteurTemplate;
	}

	public List<TemplateAddons> getTemplateAddons() {
		return this.templateAddons;
	}

	public void setTemplateAddons(List<TemplateAddons> templateAddons) {
		this.templateAddons = templateAddons;
	}

	public TemplateAddons addTemplateAddon(TemplateAddons templateAddons) {
		getTemplateAddons().add(templateAddons);
		templateAddons.setTemplate(this);

		return templateAddons;
	}

	public TemplateAddons removeTemplateAddon(TemplateAddons templateAddons) {
		getTemplateAddons().remove(templateAddons);
		templateAddons.setTemplate(null);

		return templateAddons;
	}

}