package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pack database table.
 * 
 */
@Entity
@NamedQuery(name="Pack.findAll", query="SELECT p FROM Pack p")
public class Pack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_pack")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idPack;

	@Column(name="createur_pack")
	private String createurPack;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_pack")
	private Date dateCreaPack;

	@Column(name="designation_pack")
	private String designationPack;

	private Integer memoire;

	@Column(name="nbr_users_pack")
	private Integer nbrUsersPack;

	@Column(name="prix_pack_ht")
	private float prixPackHt;

	@Column(name="prix_pack_ttc")
	private float prixPackTtc;

	private Integer stockage;

	@Column(name="tva_pack")
	private float tvaPack;

	private Integer workers;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="pack")
	private List<Commande> commandes;

	//bi-directional many-to-one association to PackType
	@ManyToOne
	@JoinColumn(name="id_packtype")
	private PackType packType;

	public Pack() {
	}

	public String getIdPack() {
		return this.idPack;
	}

	public void setIdPack(String idPack) {
		this.idPack = idPack;
	}

	public String getCreateurPack() {
		return this.createurPack;
	}

	public void setCreateurPack(String createurPack) {
		this.createurPack = createurPack;
	}

	public Date getDateCreaPack() {
		return this.dateCreaPack;
	}

	public void setDateCreaPack(Date dateCreaPack) {
		this.dateCreaPack = dateCreaPack;
	}

	public String getDesignationPack() {
		return this.designationPack;
	}

	public void setDesignationPack(String designationPack) {
		this.designationPack = designationPack;
	}

	public Integer getMemoire() {
		return this.memoire;
	}

	public void setMemoire(Integer memoire) {
		this.memoire = memoire;
	}

	public Integer getNbrUsersPack() {
		return this.nbrUsersPack;
	}

	public void setNbrUsersPack(Integer nbrUsersPack) {
		this.nbrUsersPack = nbrUsersPack;
	}

	public float getPrixPackHt() {
		return this.prixPackHt;
	}

	public void setPrixPackHt(float prixPackHt) {
		this.prixPackHt = prixPackHt;
	}

	public float getPrixPackTtc() {
		return this.prixPackTtc;
	}

	public void setPrixPackTtc(float prixPackTtc) {
		this.prixPackTtc = prixPackTtc;
	}

	public Integer getStockage() {
		return this.stockage;
	}

	public void setStockage(Integer stockage) {
		this.stockage = stockage;
	}

	public float getTvaPack() {
		return this.tvaPack;
	}

	public void setTvaPack(float tvaPack) {
		this.tvaPack = tvaPack;
	}

	public Integer getWorkers() {
		return this.workers;
	}

	public void setWorkers(Integer workers) {
		this.workers = workers;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setPack(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setPack(null);

		return commande;
	}

	public PackType getPackType() {
		return this.packType;
	}

	public void setPackType(PackType packType) {
		this.packType = packType;
	}

}