package com.vodoo.vodooapipersistance.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_splm database table.
 * 
 */
@Entity
@Table(name="service_splm")
@NamedQuery(name="ServiceSplm.findAll", query="SELECT s FROM ServiceSplm s")
public class ServiceSplm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_service")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idService;

	@Column(name="createur_service")
	private String createurService;

	@Temporal(TemporalType.DATE)
	@Column(name="date_crea_service")
	private Date dateCreaService;

	@Column(name="description_service")
	private String descriptionService;

	@Column(name="designation_service")
	private String designationService;

	//bi-directional many-to-one association to ServiceCommande
	@OneToMany(mappedBy="serviceSplm")
	private List<ServiceCommande> serviceCommandes;

	public ServiceSplm() {
	}

	public String getIdService() {
		return this.idService;
	}

	public void setIdService(String idService) {
		this.idService = idService;
	}

	public String getCreateurService() {
		return this.createurService;
	}

	public void setCreateurService(String createurService) {
		this.createurService = createurService;
	}

	public Date getDateCreaService() {
		return this.dateCreaService;
	}

	public void setDateCreaService(Date dateCreaService) {
		this.dateCreaService = dateCreaService;
	}

	public String getDescriptionService() {
		return this.descriptionService;
	}

	public void setDescriptionService(String descriptionService) {
		this.descriptionService = descriptionService;
	}

	public String getDesignationService() {
		return this.designationService;
	}

	public void setDesignationService(String designationService) {
		this.designationService = designationService;
	}

	public List<ServiceCommande> getServiceCommandes() {
		return this.serviceCommandes;
	}

	public void setServiceCommandes(List<ServiceCommande> serviceCommandes) {
		this.serviceCommandes = serviceCommandes;
	}

	public ServiceCommande addServiceCommande(ServiceCommande serviceCommande) {
		getServiceCommandes().add(serviceCommande);
		serviceCommande.setServiceSplm(this);

		return serviceCommande;
	}

	public ServiceCommande removeServiceCommande(ServiceCommande serviceCommande) {
		getServiceCommandes().remove(serviceCommande);
		serviceCommande.setServiceSplm(null);

		return serviceCommande;
	}

}