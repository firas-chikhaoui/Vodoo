package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class ReclamationDto implements Serializable {

    private String idReclamation;

    private String nature;

    private String corpsReclamation;

    private String statusReclamation;

    private String createurReclam;

    private Date dateCreaReclam;

    private ClientDto client;

}

