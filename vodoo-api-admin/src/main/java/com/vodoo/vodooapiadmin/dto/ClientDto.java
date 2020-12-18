package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;

import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto implements Serializable {

	private String idClient;

	private String nomSociete;

	private String codeFiscale;

	private Boolean fPhMo;

	private Integer tailleEtpe;

	private String createurClient;

	private Date dateCreaClient;

	private UtilisateurDto utilisateur;

}
