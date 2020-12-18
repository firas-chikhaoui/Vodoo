package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_profil database table.
 * 
 */
@Entity
@Table(name="user_profil")
@NamedQuery(name="UserProfil.findAll", query="SELECT u FROM UserProfil u")
public class UserProfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user_profil")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idUserProfil;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="id_profile")
	private Profile profile;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public UserProfil() {
	}

	public String getIdUserProfil() {
		return this.idUserProfil;
	}

	public void setIdUserProfil(String idUserProfil) {
		this.idUserProfil = idUserProfil;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}