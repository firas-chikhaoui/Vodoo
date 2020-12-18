package com.vodoo.vodooapiadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"com.vodoo.vodooapipersistance.model"})
public class VodooApiAdminApplication{

	public static void main(String[] args) {
		SpringApplication.run(VodooApiAdminApplication.class, args);
	}

}
