package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDto implements Serializable {

	private String idProfile;

	private String nomProfile;

	private String createurPofi;

	private Date dateCreaProfi;

    private Boolean isactif;

    private Boolean isdeleted ;

    private String codeProfil;

}
