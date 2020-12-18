package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_secteur database table.
 * 
 */
@Entity
@Table(name="client_secteur")
@NamedQuery(name="ClientSecteur.findAll", query="SELECT c FROM ClientSecteur c")
public class ClientSecteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_clientsecteur")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idClientsecteur;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	//bi-directional many-to-one association to Secteur
	@ManyToOne
	@JoinColumn(name="id_secteur")
	private Secteur secteur;

	public ClientSecteur() {
	}

	public String getIdClientsecteur() {
		return this.idClientsecteur;
	}

	public void setIdClientsecteur(String idClientsecteur) {
		this.idClientsecteur = idClientsecteur;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

}