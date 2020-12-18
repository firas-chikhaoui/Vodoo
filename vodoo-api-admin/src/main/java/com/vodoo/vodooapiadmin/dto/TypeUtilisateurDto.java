package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class TypeUtilisateurDto implements Serializable {

    private String idTypeUtil;

    private String createurTypeUtil;

    private Date dateCreaTypeUtil;

    private Boolean fBackend;

}

