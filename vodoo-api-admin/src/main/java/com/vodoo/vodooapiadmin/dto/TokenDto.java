package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;

import lombok.*;


@NoArgsConstructor
@Getter @Setter
public class TokenDto implements Serializable {

    private String idToken;

    private String typtoken;

    private String valueToken;

    private Integer fExpire;

    private Date dateExpiToken;

    private Date dtaeCreaToken;

    private ClientDto client;

    private UtilisateurDto utilisateur;

}

