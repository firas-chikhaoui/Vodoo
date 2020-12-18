package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the publication database table.
 * 
 */
@Entity
@NamedQuery(name="Publication.findAll", query="SELECT p FROM Publication p")
public class Publication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_publication")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idPublication;

	@Column(name="corps_publication")
	private String corpsPublication;

	@Column(name="createur_publication")
	private String createurPublication;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_publication")
	private Date dateCreaPublication;

	@Column(name="nbr_vue_publication")
	private Integer nbrVuePublication;

	@Column(name="titre_publication")
	private String titrePublication;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public Publication() {
	}

	public String getIdPublication() {
		return this.idPublication;
	}

	public void setIdPublication(String idPublication) {
		this.idPublication = idPublication;
	}

	public String getCorpsPublication() {
		return this.corpsPublication;
	}

	public void setCorpsPublication(String corpsPublication) {
		this.corpsPublication = corpsPublication;
	}

	public String getCreateurPublication() {
		return this.createurPublication;
	}

	public void setCreateurPublication(String createurPublication) {
		this.createurPublication = createurPublication;
	}

	public Date getDateCreaPublication() {
		return this.dateCreaPublication;
	}

	public void setDateCreaPublication(Date dateCreaPublication) {
		this.dateCreaPublication = dateCreaPublication;
	}

	public Integer getNbrVuePublication() {
		return this.nbrVuePublication;
	}

	public void setNbrVuePublication(Integer nbrVuePublication) {
		this.nbrVuePublication = nbrVuePublication;
	}

	public String getTitrePublication() {
		return this.titrePublication;
	}

	public void setTitrePublication(String titrePublication) {
		this.titrePublication = titrePublication;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}