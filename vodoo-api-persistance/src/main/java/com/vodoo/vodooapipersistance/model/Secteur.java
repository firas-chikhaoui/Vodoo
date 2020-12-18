package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the secteur database table.
 * 
 */
@Entity
@NamedQuery(name="Secteur.findAll", query="SELECT s FROM Secteur s")
public class Secteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_secteur")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idSecteur;

	@Column(name="createur_secteur")
	private String createurSecteur;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_secteur")
	private Date dateCreaSecteur;

	@Column(name="nom_secteur")
	private String nomSecteur;

	//bi-directional many-to-one association to ClientSecteur
	@OneToMany(mappedBy="secteur")
	private List<ClientSecteur> clientSecteurs;

	//bi-directional many-to-one association to SecteurTemplate
	@OneToMany(mappedBy="secteur")
	private List<SecteurTemplate> secteurTemplates;

	public Secteur() {
	}

	public String getIdSecteur() {
		return this.idSecteur;
	}

	public void setIdSecteur(String idSecteur) {
		this.idSecteur = idSecteur;
	}

	public String getCreateurSecteur() {
		return this.createurSecteur;
	}

	public void setCreateurSecteur(String createurSecteur) {
		this.createurSecteur = createurSecteur;
	}

	public Date getDateCreaSecteur() {
		return this.dateCreaSecteur;
	}

	public void setDateCreaSecteur(Date dateCreaSecteur) {
		this.dateCreaSecteur = dateCreaSecteur;
	}

	public String getNomSecteur() {
		return this.nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	public List<ClientSecteur> getClientSecteurs() {
		return this.clientSecteurs;
	}

	public void setClientSecteurs(List<ClientSecteur> clientSecteurs) {
		this.clientSecteurs = clientSecteurs;
	}

	public ClientSecteur addClientSecteur(ClientSecteur clientSecteur) {
		getClientSecteurs().add(clientSecteur);
		clientSecteur.setSecteur(this);

		return clientSecteur;
	}

	public ClientSecteur removeClientSecteur(ClientSecteur clientSecteur) {
		getClientSecteurs().remove(clientSecteur);
		clientSecteur.setSecteur(null);

		return clientSecteur;
	}

	public List<SecteurTemplate> getSecteurTemplates() {
		return this.secteurTemplates;
	}

	public void setSecteurTemplates(List<SecteurTemplate> secteurTemplates) {
		this.secteurTemplates = secteurTemplates;
	}

	public SecteurTemplate addSecteurTemplate(SecteurTemplate secteurTemplate) {
		getSecteurTemplates().add(secteurTemplate);
		secteurTemplate.setSecteur(this);

		return secteurTemplate;
	}

	public SecteurTemplate removeSecteurTemplate(SecteurTemplate secteurTemplate) {
		getSecteurTemplates().remove(secteurTemplate);
		secteurTemplate.setSecteur(null);

		return secteurTemplate;
	}

}