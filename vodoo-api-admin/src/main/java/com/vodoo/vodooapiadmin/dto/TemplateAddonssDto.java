package com.vodoo.vodooapiadmin.dto;

import com.vodoo.vodooapipersistance.model.Addons;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TemplateAddonssDto {

    private String idTemplate;
    private List<String> idsAddons ;
}
