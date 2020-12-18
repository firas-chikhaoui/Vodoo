package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class ProfilFoncDto implements Serializable {

	private String idProfilFonc;

	private Integer droitAcces;

	private String createurProfilFonc;

	private Date dateCreaProfilFonc;

	private FonctionDto fonction;

	private ProfileDto profile;

}
