package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class ServiceSplmDto implements Serializable {

    private String idService;

    private String designationService;

    private String descriptionService;

    private String createurService;

    private Date dateCreaService;

}

