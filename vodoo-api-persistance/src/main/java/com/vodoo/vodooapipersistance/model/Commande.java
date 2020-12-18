package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_commande")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idCommande;

	@Column(name="code_commande")
	private String codeCommande;

	@Column(name="createur_commande")
	private String createurCommande;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_commande")
	private Date dateCreaCommande;

	@Column(name="nbr_util")
	private Integer nbrUtil;

	@Column(name="nbr_workers")
	private Integer nbrWorkers;

	@Column(name="prix_cmd_ht")
	private float prixCmdHt;

	@Column(name="prix_cmd_ttc")
	private float prixCmdTtc;

	@Temporal(TemporalType.DATE)
	@Column(name="removal_date")
	private Date removalDate;

	@Column(name="taille_memoire")
	private Integer tailleMemoire;

	@Column(name="taille_stockage")
	private Integer tailleStockage;

	@Column(name="tva_cmd")
	private float tvaCmd;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	//bi-directional many-to-one association to Pack
	@ManyToOne
	@JoinColumn(name="id_pack")
	private Pack pack;

	//bi-directional many-to-one association to Template
	@ManyToOne
	@JoinColumn(name="id_template")
	private Template template;

	//bi-directional many-to-one association to Environnement
	@OneToMany(mappedBy="commande")
	private List<Environnement> environnements;

	//bi-directional many-to-one association to ServiceCommande
	@OneToMany(mappedBy="commande")
	private List<ServiceCommande> serviceCommandes;

	public Commande() {
	}

	public String getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(String idCommande) {
		this.idCommande = idCommande;
	}

	public String getCodeCommande() {
		return this.codeCommande;
	}

	public void setCodeCommande(String codeCommande) {
		this.codeCommande = codeCommande;
	}

	public String getCreateurCommande() {
		return this.createurCommande;
	}

	public void setCreateurCommande(String createurCommande) {
		this.createurCommande = createurCommande;
	}

	public Date getDateCreaCommande() {
		return this.dateCreaCommande;
	}

	public void setDateCreaCommande(Date dateCreaCommande) {
		this.dateCreaCommande = dateCreaCommande;
	}

	public Integer getNbrUtil() {
		return this.nbrUtil;
	}

	public void setNbrUtil(Integer nbrUtil) {
		this.nbrUtil = nbrUtil;
	}

	public Integer getNbrWorkers() {
		return this.nbrWorkers;
	}

	public void setNbrWorkers(Integer nbrWorkers) {
		this.nbrWorkers = nbrWorkers;
	}

	public float getPrixCmdHt() {
		return this.prixCmdHt;
	}

	public void setPrixCmdHt(float prixCmdHt) {
		this.prixCmdHt = prixCmdHt;
	}

	public float getPrixCmdTtc() {
		return this.prixCmdTtc;
	}

	public void setPrixCmdTtc(float prixCmdTtc) {
		this.prixCmdTtc = prixCmdTtc;
	}

	public Date getRemovalDate() {
		return this.removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public Integer getTailleMemoire() {
		return this.tailleMemoire;
	}

	public void setTailleMemoire(Integer tailleMemoire) {
		this.tailleMemoire = tailleMemoire;
	}

	public Integer getTailleStockage() {
		return this.tailleStockage;
	}

	public void setTailleStockage(Integer tailleStockage) {
		this.tailleStockage = tailleStockage;
	}

	public float getTvaCmd() {
		return this.tvaCmd;
	}

	public void setTvaCmd(float tvaCmd) {
		this.tvaCmd = tvaCmd;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Pack getPack() {
		return this.pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Environnement> getEnvironnements() {
		return this.environnements;
	}

	public void setEnvironnements(List<Environnement> environnements) {
		this.environnements = environnements;
	}

	public Environnement addEnvironnement(Environnement environnement) {
		getEnvironnements().add(environnement);
		environnement.setCommande(this);

		return environnement;
	}

	public Environnement removeEnvironnement(Environnement environnement) {
		getEnvironnements().remove(environnement);
		environnement.setCommande(null);

		return environnement;
	}

	public List<ServiceCommande> getServiceCommandes() {
		return this.serviceCommandes;
	}

	public void setServiceCommandes(List<ServiceCommande> serviceCommandes) {
		this.serviceCommandes = serviceCommandes;
	}

	public ServiceCommande addServiceCommande(ServiceCommande serviceCommande) {
		getServiceCommandes().add(serviceCommande);
		serviceCommande.setCommande(this);

		return serviceCommande;
	}

	public ServiceCommande removeServiceCommande(ServiceCommande serviceCommande) {
		getServiceCommandes().remove(serviceCommande);
		serviceCommande.setCommande(null);

		return serviceCommande;
	}

}