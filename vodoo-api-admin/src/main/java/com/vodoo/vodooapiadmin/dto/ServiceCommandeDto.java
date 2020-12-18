package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class ServiceCommandeDto implements Serializable {

    private String idServiceCommande;

    private String createurServiceCommande;

    private Date dateCreaServCmd;

    private CommandeDto commande;

    private ServiceSplmDto serviceSplm;

}

