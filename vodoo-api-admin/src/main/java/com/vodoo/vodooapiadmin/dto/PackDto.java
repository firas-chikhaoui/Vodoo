package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import lombok.*;


@NoArgsConstructor
@Getter @Setter
public class PackDto implements Serializable {

    private String idPack;

    private String designationPack;

    private Integer nbrUsersPack;

    private Integer workers;

    private Integer memoire;

    private Integer stockage;

    private Float prixPackHt;

    private Float prixPackTtc;

    private Float tvaPack;

    private String createurPack;

    private Date dateCreaPack;

    private PackTypeDto packType;

}

