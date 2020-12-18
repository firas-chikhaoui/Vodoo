package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;

import com.vodoo.vodooapiadmin.dto.UtilisateurDto;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class PublicationDto implements Serializable {

	private String idPublication;

	private String titrePublication;

	private String corpsPublication;

	private Date dateCreaPublication;

	private String createurPublication;

	private Integer nbrVuePublication;

	private UtilisateurDto utilisateur;

}
