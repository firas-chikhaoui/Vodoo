package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reclamation database table.
 * 
 */
@Entity
@NamedQuery(name="Reclamation.findAll", query="SELECT r FROM Reclamation r")
public class Reclamation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_reclamation")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idReclamation;

	@Column(name="corps_reclamation")
	private String corpsReclamation;

	@Column(name="createur_reclam")
	private String createurReclam;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_reclam")
	private Date dateCreaReclam;

	private String nature;

	@Column(name="status_reclamation")
	private String statusReclamation;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;

	public Reclamation() {
	}

	public String getIdReclamation() {
		return this.idReclamation;
	}

	public void setIdReclamation(String idReclamation) {
		this.idReclamation = idReclamation;
	}

	public String getCorpsReclamation() {
		return this.corpsReclamation;
	}

	public void setCorpsReclamation(String corpsReclamation) {
		this.corpsReclamation = corpsReclamation;
	}

	public String getCreateurReclam() {
		return this.createurReclam;
	}

	public void setCreateurReclam(String createurReclam) {
		this.createurReclam = createurReclam;
	}

	public Date getDateCreaReclam() {
		return this.dateCreaReclam;
	}

	public void setDateCreaReclam(Date dateCreaReclam) {
		this.dateCreaReclam = dateCreaReclam;
	}

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getStatusReclamation() {
		return this.statusReclamation;
	}

	public void setStatusReclamation(String statusReclamation) {
		this.statusReclamation = statusReclamation;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}