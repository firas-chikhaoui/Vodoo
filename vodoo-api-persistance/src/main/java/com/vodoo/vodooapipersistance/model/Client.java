package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_client")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idClient;

	@Column(name="code_fiscale")
	private String codeFiscale;

	@Column(name="createur_client")
	private String createurClient;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_client")
	private Date dateCreaClient;

	@Column(name="f_ph_mo")
	private Boolean fPhMo;

	@Column(name="nom_societe")
	private String nomSociete;

	@Column(name="taille_etpe")
	private Integer tailleEtpe;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to ClientSecteur
	@OneToMany(mappedBy="client")
	private List<ClientSecteur> clientSecteurs;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="client")
	private List<Commande> commandes;

	//bi-directional many-to-one association to Reclamation
	@OneToMany(mappedBy="client")
	private List<Reclamation> reclamations;

	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="client")
	private List<Token> tokens;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="client")
	private List<Utilisateur> utilisateurs;

	public Client() {
	}

	public String getIdClient() {
		return this.idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getCodeFiscale() {
		return this.codeFiscale;
	}

	public void setCodeFiscale(String codeFiscale) {
		this.codeFiscale = codeFiscale;
	}

	public String getCreateurClient() {
		return this.createurClient;
	}

	public void setCreateurClient(String createurClient) {
		this.createurClient = createurClient;
	}

	public Date getDateCreaClient() {
		return this.dateCreaClient;
	}

	public void setDateCreaClient(Date dateCreaClient) {
		this.dateCreaClient = dateCreaClient;
	}

	public Boolean getFPhMo() {
		return this.fPhMo;
	}

	public void setFPhMo(Boolean fPhMo) {
		this.fPhMo = fPhMo;
	}

	public String getNomSociete() {
		return this.nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public Integer getTailleEtpe() {
		return this.tailleEtpe;
	}

	public void setTailleEtpe(Integer tailleEtpe) {
		this.tailleEtpe = tailleEtpe;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<ClientSecteur> getClientSecteurs() {
		return this.clientSecteurs;
	}

	public void setClientSecteurs(List<ClientSecteur> clientSecteurs) {
		this.clientSecteurs = clientSecteurs;
	}

	public ClientSecteur addClientSecteur(ClientSecteur clientSecteur) {
		getClientSecteurs().add(clientSecteur);
		clientSecteur.setClient(this);

		return clientSecteur;
	}

	public ClientSecteur removeClientSecteur(ClientSecteur clientSecteur) {
		getClientSecteurs().remove(clientSecteur);
		clientSecteur.setClient(null);

		return clientSecteur;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setClient(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setClient(null);

		return commande;
	}

	public List<Reclamation> getReclamations() {
		return this.reclamations;
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public Reclamation addReclamation(Reclamation reclamation) {
		getReclamations().add(reclamation);
		reclamation.setClient(this);

		return reclamation;
	}

	public Reclamation removeReclamation(Reclamation reclamation) {
		getReclamations().remove(reclamation);
		reclamation.setClient(null);

		return reclamation;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setClient(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setClient(null);

		return token;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setClient(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setClient(null);

		return utilisateur;
	}

}