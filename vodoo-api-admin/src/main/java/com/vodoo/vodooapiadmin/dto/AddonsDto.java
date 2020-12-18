package com.vodoo.vodooapiadmin.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter
public class AddonsDto implements Serializable {

    private String idAddon;

    private String nomAddon;

    private String descriptionAdon;

    private String createurAddon;

    private Date dtaeCreaAddon;

}

