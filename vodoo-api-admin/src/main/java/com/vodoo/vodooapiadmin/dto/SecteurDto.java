package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class SecteurDto implements Serializable {

	private String idSecteur;

	private String nomSecteur;

	private String createurSecteur;

	private Date dateCreaSecteur;

}
