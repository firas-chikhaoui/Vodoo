package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_utilisateur")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idUtilisateur;

	private String login;

	private String email;

	private String nom;

	private String prenom;

	private String pwd;

	private Integer isactif;

	private Integer isdeleted;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="utilisateur")
	private List<Client> clients;

	//bi-directional many-to-one association to Publication
	@OneToMany(mappedBy="utilisateur")
	private List<Publication> publications;

	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="utilisateur")
	private List<Token> tokens;

	//bi-directional many-to-one association to UserProfil
	@OneToMany(mappedBy="utilisateur")
	private List<UserProfil> userProfils;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	//bi-directional many-to-one association to TypeUtilisateur
	@ManyToOne
	@JoinColumn(name="id_type_util")
	private TypeUtilisateur typeUtilisateur;

	public Utilisateur() {
	}

	public String getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setUtilisateur(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setUtilisateur(null);

		return client;
	}

	public List<Publication> getPublications() {
		return this.publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public Publication addPublication(Publication publication) {
		getPublications().add(publication);
		publication.setUtilisateur(this);

		return publication;
	}

	public Publication removePublication(Publication publication) {
		getPublications().remove(publication);
		publication.setUtilisateur(null);

		return publication;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setUtilisateur(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setUtilisateur(null);

		return token;
	}

	public List<UserProfil> getUserProfils() {
		return this.userProfils;
	}

	public void setUserProfils(List<UserProfil> userProfils) {
		this.userProfils = userProfils;
	}

	public UserProfil addUserProfil(UserProfil userProfil) {
		getUserProfils().add(userProfil);
		userProfil.setUtilisateur(this);

		return userProfil;
	}

	public UserProfil removeUserProfil(UserProfil userProfil) {
		getUserProfils().remove(userProfil);
		userProfil.setUtilisateur(null);

		return userProfil;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TypeUtilisateur getTypeUtilisateur() {
		return this.typeUtilisateur;
	}

	public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
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