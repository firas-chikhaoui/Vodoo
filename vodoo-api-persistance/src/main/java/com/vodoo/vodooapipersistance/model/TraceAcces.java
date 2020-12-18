package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the trace_acces database table.
 * 
 */
@Entity
@Table(name="trace_acces")
@NamedQuery(name="TraceAcce.findAll", query="SELECT t FROM TraceAcces t")
public class TraceAcces implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_trace")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idTrace;

	@Temporal(TemporalType.DATE)
	@Column(name="date_trace")
	private Date dateTrace;

	@Column(name="identifiant_trace")
	private String identifiantTrace;

	private String ip;

	private String navigateur;

	private String status;

	@Column(name="version_nav")
	private String versionNav;

	public TraceAcces() {
	}

	public String getIdTrace() {
		return this.idTrace;
	}

	public void setIdTrace(String idTrace) {
		this.idTrace = idTrace;
	}

	public Date getDateTrace() {
		return this.dateTrace;
	}

	public void setDateTrace(Date dateTrace) {
		this.dateTrace = dateTrace;
	}

	public String getIdentifiantTrace() {
		return this.identifiantTrace;
	}

	public void setIdentifiantTrace(String identifiantTrace) {
		this.identifiantTrace = identifiantTrace;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNavigateur() {
		return this.navigateur;
	}

	public void setNavigateur(String navigateur) {
		this.navigateur = navigateur;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionNav() {
		return this.versionNav;
	}

	public void setVersionNav(String versionNav) {
		this.versionNav = versionNav;
	}

}