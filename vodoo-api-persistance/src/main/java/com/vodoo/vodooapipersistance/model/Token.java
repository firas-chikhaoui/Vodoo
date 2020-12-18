package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@NamedQuery(name="Token.findAll", query="SELECT t FROM Token t")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_token")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idToken;

	@Temporal(TemporalType.DATE)
	@Column(name="date_expi_token")
	private Date dateExpiToken;

	@Temporal(TemporalType.DATE)
	@Column(name="dtae_crea_token")
	private Date dtaeCreaToken;

	@Column(name="f_expire")
	private Integer fExpire;

	private String typtoken;

	@Column(name="value_token")
	private String valueToken;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public Token() {
	}

	public String getIdToken() {
		return this.idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public Date getDateExpiToken() {
		return this.dateExpiToken;
	}

	public void setDateExpiToken(Date dateExpiToken) {
		this.dateExpiToken = dateExpiToken;
	}

	public Date getDtaeCreaToken() {
		return this.dtaeCreaToken;
	}

	public void setDtaeCreaToken(Date dtaeCreaToken) {
		this.dtaeCreaToken = dtaeCreaToken;
	}

	public Integer getFExpire() {
		return this.fExpire;
	}

	public void setFExpire(Integer fExpire) {
		this.fExpire = fExpire;
	}

	public String getTyptoken() {
		return this.typtoken;
	}

	public void setTyptoken(String typtoken) {
		this.typtoken = typtoken;
	}

	public String getValueToken() {
		return this.valueToken;
	}

	public void setValueToken(String valueToken) {
		this.valueToken = valueToken;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}