package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import lombok.*;


@NoArgsConstructor
@Getter @Setter
public class UserProfilDto implements Serializable {

    private String idUserProfil;

    private ProfileDto profile;

    private UtilisateurDto utilisateur;

}

