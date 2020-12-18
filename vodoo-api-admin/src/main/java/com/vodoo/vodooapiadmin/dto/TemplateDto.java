package com.vodoo.vodooapiadmin.dto;

import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class TemplateDto implements Serializable {

	private String idTemplate;

	private String designationTemplate;

	private String filename;

	private String createurTemplate;

	private Date dateCreaTemplate;

}
