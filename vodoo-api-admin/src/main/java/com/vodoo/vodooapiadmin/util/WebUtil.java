package com.vodoo.vodooapiadmin.util;

import com.vodoo.vodooapipersistance.model.TraceAcces;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class WebUtil {
	
	 public static TraceAcces getClientIp(HttpServletRequest request) {

	        String remoteAddr = "";
		 TraceAcces log= new TraceAcces();
	        if (request != null) {
	        	
	        	UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
				if(userAgent.getBrowserVersion()!=null && userAgent.getBrowser() !=null){
					log.setNavigateur(userAgent.getBrowser().getName());
					log.setVersionNav(userAgent.getBrowserVersion().toString());
					log.setDateTrace(new Date());
					remoteAddr = request.getHeader("X-FORWARDED-FOR");
				}
	            if (remoteAddr == null || "".equals(remoteAddr)) {
	            	log.setIp(request.getRemoteAddr()) ;
	            }
	        }

	        return log;
	    }

}
