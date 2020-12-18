package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class TemplateAddonsDto implements Serializable {

    private String idTemplateAddons;

    private AddonsDto addons;

    private TemplateDto template;

}

