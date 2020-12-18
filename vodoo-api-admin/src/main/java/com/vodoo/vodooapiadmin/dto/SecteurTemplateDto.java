package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class SecteurTemplateDto implements Serializable {

    private String idSecteurTemplate;

    private SecteurDto secteur;

    private TemplateDto template;

}

