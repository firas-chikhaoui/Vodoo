package com.vodoo.vodooapiadmin.dto;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter @Setter
public class TraceAccesDto implements Serializable {

    private String idTrace;

    private String identifiantTrace;

    private String ip;

    private String navigateur;

    private String versionNav;

    private String status;

    private Date dateTrace;

}

