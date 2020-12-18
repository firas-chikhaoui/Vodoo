package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the profil_fonc database table.
 * 
 */
@Entity
@Table(name="profil_fonc")
@NamedQuery(name="ProfilFonc.findAll", query="SELECT p FROM ProfilFonc p")
public class ProfilFonc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_profil_fonc")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idProfilFonc;

	@Column(name="createur_profil_fonc")
	private String createurProfilFonc;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_profil_fonc")
	private Date dateCreaProfilFonc;

	@Column(name="droit_acces")
	private Integer droitAcces;

	//bi-directional many-to-one association to Fonction
	@ManyToOne
	@JoinColumn(name="id_fonc")
	private Fonction fonction;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="id_profile")
	private Profile profile;

	public ProfilFonc() {
	}

	public String getIdProfilFonc() {
		return this.idProfilFonc;
	}

	public void setIdProfilFonc(String idProfilFonc) {
		this.idProfilFonc = idProfilFonc;
	}

	public String getCreateurProfilFonc() {
		return this.createurProfilFonc;
	}

	public void setCreateurProfilFonc(String createurProfilFonc) {
		this.createurProfilFonc = createurProfilFonc;
	}

	public Date getDateCreaProfilFonc() {
		return this.dateCreaProfilFonc;
	}

	public void setDateCreaProfilFonc(Date dateCreaProfilFonc) {
		this.dateCreaProfilFonc = dateCreaProfilFonc;
	}

	public Integer getDroitAcces() {
		return this.droitAcces;
	}

	public void setDroitAcces(Integer droitAcces) {
		this.droitAcces = droitAcces;
	}

	public Fonction getFonction() {
		return this.fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}