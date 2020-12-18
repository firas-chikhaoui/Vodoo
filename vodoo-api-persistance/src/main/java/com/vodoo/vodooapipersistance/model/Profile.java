package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_profile")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idProfile;

	@Column(name="createur_pofi")
	private String createurPofi;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_profi")
	private Date dateCreaProfi;

	@Column(name="nom_profile")
	private String nomProfile;

	//bi-directional many-to-one association to ProfilFonc
	@OneToMany(mappedBy="profile")
	private List<ProfilFonc> profilFoncs;

	//bi-directional many-to-one association to UserProfil
	@OneToMany(mappedBy="profile")
	private List<UserProfil> userProfils;

	@Column(name="code")
	private String codeProfil;

	private Integer isactif;

	private Integer isdeleted;


	public Profile() {
	}

	public String getIdProfile() {
		return this.idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	public String getCreateurPofi() {
		return this.createurPofi;
	}

	public void setCreateurPofi(String createurPofi) {
		this.createurPofi = createurPofi;
	}

	public Date getDateCreaProfi() {
		return this.dateCreaProfi;
	}

	public void setDateCreaProfi(Date dateCreaProfi) {
		this.dateCreaProfi = dateCreaProfi;
	}

	public String getNomProfile() {
		return this.nomProfile;
	}

	public void setNomProfile(String nomProfile) {
		this.nomProfile = nomProfile;
	}

	public List<ProfilFonc> getProfilFoncs() {
		return this.profilFoncs;
	}

	public void setProfilFoncs(List<ProfilFonc> profilFoncs) {
		this.profilFoncs = profilFoncs;
	}

	public ProfilFonc addProfilFonc(ProfilFonc profilFonc) {
		getProfilFoncs().add(profilFonc);
		profilFonc.setProfile(this);

		return profilFonc;
	}

	public ProfilFonc removeProfilFonc(ProfilFonc profilFonc) {
		getProfilFoncs().remove(profilFonc);
		profilFonc.setProfile(null);

		return profilFonc;
	}

	public List<UserProfil> getUserProfils() {
		return this.userProfils;
	}

	public void setUserProfils(List<UserProfil> userProfils) {
		this.userProfils = userProfils;
	}

	public UserProfil addUserProfil(UserProfil userProfil) {
		getUserProfils().add(userProfil);
		userProfil.setProfile(this);

		return userProfil;
	}

	public UserProfil removeUserProfil(UserProfil userProfil) {
		getUserProfils().remove(userProfil);
		userProfil.setProfile(null);

		return userProfil;
	}

	public String getCodeProfil() {
		return codeProfil;
	}

	public void setCodeProfil(String codeProfil) {
		this.codeProfil = codeProfil;
	}

	public Integer getIsactif() {
		return isactif;
	}

	public void setIsactif(Integer isactif) {
		this.isactif = isactif;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
}