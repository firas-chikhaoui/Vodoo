package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import lombok.*;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter @Setter
public class CommandeDto implements Serializable {

    private String idCommande;

    private Float prixCmdHt;

    private Float prixCmdTtc;

    private Float tvaCmd;

    @NotNull(message = "codeCommande ne peut pas être null")
    private String codeCommande;

    private String createurCommande;

    private Date dateCreaCommande;

    private Date removalDate;

    private Integer nbrUtil;

    private Integer nbrWorkers;

    private Integer tailleMemoire;

    private Integer tailleStockage;

    private ClientDto client;

    private PackDto pack;

    private TemplateDto template;

}

