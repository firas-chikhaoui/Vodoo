package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class ClientSecteurDto implements Serializable {

    private String idClientsecteur;

    private ClientDto client;

    private SecteurDto secteur;

}

