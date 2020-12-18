package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the addons database table.
 * 
 */
@Entity
@Table(name="addons")
public class Addons implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_addon")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idAddon;

	@Column(name="createur_addon")
	private String createurAddon;

	@Column(name="description_adon")
	private String descriptionAdon;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_addon")
	private Date dtaeCreaAddon;

	@Column(name="nom_addon")
	private String nomAddon;

	//bi-directional many-to-one association to TemplateAddon
	@OneToMany(mappedBy="addons")
	private List<TemplateAddons> templateAddons;

	public Addons() {
	}

	public String getIdAddon() {
		return this.idAddon;
	}

	public void setIdAddon(String idAddon) {
		this.idAddon = idAddon;
	}

	public String getCreateurAddon() {
		return this.createurAddon;
	}

	public void setCreateurAddon(String createurAddon) {
		this.createurAddon = createurAddon;
	}

	public String getDescriptionAdon() {
		return this.descriptionAdon;
	}

	public void setDescriptionAdon(String descriptionAdon) {
		this.descriptionAdon = descriptionAdon;
	}

	public Date getDtaeCreaAddon() {
		return this.dtaeCreaAddon;
	}

	public void setDtaeCreaAddon(Date dtaeCreaAddon) {
		this.dtaeCreaAddon = dtaeCreaAddon;
	}

	public String getNomAddon() {
		return this.nomAddon;
	}

	public void setNomAddon(String nomAddon) {
		this.nomAddon = nomAddon;
	}

	public List<TemplateAddons> getTemplateAddons() {
		return this.templateAddons;
	}

	public void setTemplateAddons(List<TemplateAddons> templateAddons) {
		this.templateAddons = templateAddons;
	}

	public TemplateAddons addTemplateAddon(TemplateAddons templateAddons) {
		getTemplateAddons().add(templateAddons);
		templateAddons.setAddons(this);

		return templateAddons;
	}

	public TemplateAddons removeTemplateAddon(TemplateAddons templateAddons) {
		getTemplateAddons().remove(templateAddons);
		templateAddons.setAddons(null);

		return templateAddons;
	}

}