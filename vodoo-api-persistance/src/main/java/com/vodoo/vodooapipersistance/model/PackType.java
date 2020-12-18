package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pack_type database table.
 * 
 */
@Entity
@Table(name="pack_type")
@NamedQuery(name="PackType.findAll", query="SELECT p FROM PackType p")
public class PackType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_packtype")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idPacktype;

	private String typepack;

	//bi-directional many-to-one association to Pack
	@OneToMany(mappedBy="packType")
	private List<Pack> packs;

	public PackType() {
	}

	public String getIdPacktype() {
		return this.idPacktype;
	}

	public void setIdPacktype(String idPacktype) {
		this.idPacktype = idPacktype;
	}

	public String getTypepack() {
		return this.typepack;
	}

	public void setTypepack(String typepack) {
		this.typepack = typepack;
	}

	public List<Pack> getPacks() {
		return this.packs;
	}

	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}

	public Pack addPack(Pack pack) {
		getPacks().add(pack);
		pack.setPackType(this);

		return pack;
	}

	public Pack removePack(Pack pack) {
		getPacks().remove(pack);
		pack.setPackType(null);

		return pack;
	}

}