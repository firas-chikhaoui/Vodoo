package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the env_type database table.
 * 
 */
@Entity
@Table(name="env_type")
@NamedQuery(name="EnvType.findAll", query="SELECT e FROM EnvType e")
public class EnvType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_envtype")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idEnvtype;

	@Column(name="type_env")
	private String typeEnv;

	//bi-directional many-to-one association to Environnement
	@OneToMany(mappedBy="envType")
	private List<Environnement> environnements;

	public EnvType() {
	}

	public String getIdEnvtype() {
		return this.idEnvtype;
	}

	public void setIdEnvtype(String idEnvtype) {
		this.idEnvtype = idEnvtype;
	}

	public String getTypeEnv() {
		return this.typeEnv;
	}

	public void setTypeEnv(String typeEnv) {
		this.typeEnv = typeEnv;
	}

	public List<Environnement> getEnvironnements() {
		return this.environnements;
	}

	public void setEnvironnements(List<Environnement> environnements) {
		this.environnements = environnements;
	}

	public Environnement addEnvironnement(Environnement environnement) {
		getEnvironnements().add(environnement);
		environnement.setEnvType(this);

		return environnement;
	}

	public Environnement removeEnvironnement(Environnement environnement) {
		getEnvironnements().remove(environnement);
		environnement.setEnvType(null);

		return environnement;
	}

}