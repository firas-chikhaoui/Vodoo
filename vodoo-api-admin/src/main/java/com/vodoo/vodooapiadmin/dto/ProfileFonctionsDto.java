package com.vodoo.vodooapiadmin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProfileFonctionsDto {
    private String idProfile;
    private List<String> idsFonctions;
}
