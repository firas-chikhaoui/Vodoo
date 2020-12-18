package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the service_commande database table.
 * 
 */
@Entity
@Table(name="service_commande")
@NamedQuery(name="ServiceCommande.findAll", query="SELECT s FROM ServiceCommande s")
public class ServiceCommande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_service_commande")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idServiceCommande;

	@Column(name="createur_service_commande")
	private String createurServiceCommande;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_serv_cmd")
	private Date dateCreaServCmd;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_commande")
	private Commande commande;

	//bi-directional many-to-one association to ServiceSplm
	@ManyToOne
	@JoinColumn(name="id_service")
	private ServiceSplm serviceSplm;

	public ServiceCommande() {
	}

	public String getIdServiceCommande() {
		return this.idServiceCommande;
	}

	public void setIdServiceCommande(String idServiceCommande) {
		this.idServiceCommande = idServiceCommande;
	}

	public String getCreateurServiceCommande() {
		return this.createurServiceCommande;
	}

	public void setCreateurServiceCommande(String createurServiceCommande) {
		this.createurServiceCommande = createurServiceCommande;
	}

	public Date getDateCreaServCmd() {
		return this.dateCreaServCmd;
	}

	public void setDateCreaServCmd(Date dateCreaServCmd) {
		this.dateCreaServCmd = dateCreaServCmd;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public ServiceSplm getServiceSplm() {
		return this.serviceSplm;
	}

	public void setServiceSplm(ServiceSplm serviceSplm) {
		this.serviceSplm = serviceSplm;
	}

}