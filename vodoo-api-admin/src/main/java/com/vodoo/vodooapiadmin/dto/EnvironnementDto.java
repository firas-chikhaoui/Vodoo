package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class EnvironnementDto implements Serializable {

    private String idEnv;

    private String createurEnv;

    private Date dateCreaEnv;

    private String descriptionEnv;

    private CommandeDto commande;

    private EnvTypeDto envType;

}

