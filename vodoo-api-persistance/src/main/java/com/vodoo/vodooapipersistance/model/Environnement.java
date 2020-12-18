package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the environnement database table.
 * 
 */
@Entity
@NamedQuery(name="Environnement.findAll", query="SELECT e FROM Environnement e")
public class Environnement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_env")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idEnv;

	@Column(name="createur_env")
	private String createurEnv;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_env")
	private Date dateCreaEnv;

	@Column(name="description_env")
	private String descriptionEnv;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_commande")
	private Commande commande;

	//bi-directional many-to-one association to EnvType
	@ManyToOne
	@JoinColumn(name="id_envtype")
	private EnvType envType;

	public Environnement() {
	}

	public String getIdEnv() {
		return this.idEnv;
	}

	public void setIdEnv(String idEnv) {
		this.idEnv = idEnv;
	}

	public String getCreateurEnv() {
		return this.createurEnv;
	}

	public void setCreateurEnv(String createurEnv) {
		this.createurEnv = createurEnv;
	}

	public Date getDateCreaEnv() {
		return this.dateCreaEnv;
	}

	public void setDateCreaEnv(Date dateCreaEnv) {
		this.dateCreaEnv = dateCreaEnv;
	}

	public String getDescriptionEnv() {
		return this.descriptionEnv;
	}

	public void setDescriptionEnv(String descriptionEnv) {
		this.descriptionEnv = descriptionEnv;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public EnvType getEnvType() {
		return this.envType;
	}

	public void setEnvType(EnvType envType) {
		this.envType = envType;
	}

}