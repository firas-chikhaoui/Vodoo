package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import lombok.*;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter @Setter
public class UtilisateurDto implements Serializable {

    private String idUtilisateur;

    private String nom;

    private String prenom;

    @NotNull(message = "codeUser ne peut pas être null")
    private String codeUser;

    private String email;

    private String pwd;

    private ClientDto client;

    private TypeUtilisateurDto typeUtilisateur;

}

