package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the type_utilisateur database table.
 * 
 */
@Entity
@Table(name="type_utilisateur")
@NamedQuery(name="TypeUtilisateur.findAll", query="SELECT t FROM TypeUtilisateur t")
public class TypeUtilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type_util")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idTypeUtil;

	@Column(name="createur_type_util")
	private String createurTypeUtil;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_type_util")
	private Date dateCreaTypeUtil;

	@Column(name="f_backend")
	private Integer fBackend;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="typeUtilisateur")
	private List<Utilisateur> utilisateurs;

	public TypeUtilisateur() {
	}

	public String getIdTypeUtil() {
		return this.idTypeUtil;
	}

	public void setIdTypeUtil(String idTypeUtil) {
		this.idTypeUtil = idTypeUtil;
	}

	public String getCreateurTypeUtil() {
		return this.createurTypeUtil;
	}

	public void setCreateurTypeUtil(String createurTypeUtil) {
		this.createurTypeUtil = createurTypeUtil;
	}

	public Date getDateCreaTypeUtil() {
		return this.dateCreaTypeUtil;
	}

	public void setDateCreaTypeUtil(Date dateCreaTypeUtil) {
		this.dateCreaTypeUtil = dateCreaTypeUtil;
	}


	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setTypeUtilisateur(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setTypeUtilisateur(null);

		return utilisateur;
	}

	public Integer getfBackend() {
		return fBackend;
	}

	public void setfBackend(Integer fBackend) {
		this.fBackend = fBackend;
	}
}