package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fonction database table.
 * 
 */
@Entity
@NamedQuery(name="Fonction.findAll", query="SELECT f FROM Fonction f")
public class Fonction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_fonc")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idFonc;

	@Column(name="code_fonc")
	private String codeFonc;

	@Column(name="createur_fonc")
	private String createurFonc;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_fonc")
	private Date dateCreaFonc;

	@Column(name="nom_fonc")
	private String nomFonc;

	//bi-directional many-to-one association to Fonction
	@ManyToOne
	@JoinColumn(name="id_fonc_mere")
	private Fonction fonction;

	//bi-directional many-to-one association to Fonction
	@OneToMany(mappedBy="fonction")
	private List<Fonction> fonctions;

	//bi-directional many-to-one association to ProfilFonc
	@OneToMany(mappedBy="fonction")
	private List<ProfilFonc> profilFoncs;

	public Fonction() {
	}

	public String getIdFonc() {
		return this.idFonc;
	}

	public void setIdFonc(String idFonc) {
		this.idFonc = idFonc;
	}

	public String getCodeFonc() {
		return this.codeFonc;
	}

	public void setCodeFonc(String codeFonc) {
		this.codeFonc = codeFonc;
	}

	public String getCreateurFonc() {
		return this.createurFonc;
	}

	public void setCreateurFonc(String createurFonc) {
		this.createurFonc = createurFonc;
	}

	public Date getDateCreaFonc() {
		return this.dateCreaFonc;
	}

	public void setDateCreaFonc(Date dateCreaFonc) {
		this.dateCreaFonc = dateCreaFonc;
	}

	public String getNomFonc() {
		return this.nomFonc;
	}

	public void setNomFonc(String nomFonc) {
		this.nomFonc = nomFonc;
	}

	public Fonction getFonction() {
		return this.fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public List<Fonction> getFonctions() {
		return this.fonctions;
	}

	public void setFonctions(List<Fonction> fonctions) {
		this.fonctions = fonctions;
	}

	public Fonction addFonction(Fonction fonction) {
		getFonctions().add(fonction);
		fonction.setFonction(this);

		return fonction;
	}

	public Fonction removeFonction(Fonction fonction) {
		getFonctions().remove(fonction);
		fonction.setFonction(null);

		return fonction;
	}

	public List<ProfilFonc> getProfilFoncs() {
		return this.profilFoncs;
	}

	public void setProfilFoncs(List<ProfilFonc> profilFoncs) {
		this.profilFoncs = profilFoncs;
	}

	public ProfilFonc addProfilFonc(ProfilFonc profilFonc) {
		getProfilFoncs().add(profilFonc);
		profilFonc.setFonction(this);

		return profilFonc;
	}

	public ProfilFonc removeProfilFonc(ProfilFonc profilFonc) {
		getProfilFoncs().remove(profilFonc);
		profilFonc.setFonction(null);

		return profilFonc;
	}

}