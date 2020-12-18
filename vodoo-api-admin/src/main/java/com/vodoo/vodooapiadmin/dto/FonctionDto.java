package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class FonctionDto implements Serializable {

	private String idFonc;

	private String nomFonc;

	@NotNull(message = "codeFonc ne peut pas être null")
	private String codeFonc;

	private String createurFonc;

	private Date dateCreaFonc;

}
