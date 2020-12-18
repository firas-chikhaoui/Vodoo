package com.vodoo.vodooapiadmin.dto;

public class ResetPasswordDto {
	 private String iduser;
	    private String password;

	    public ResetPasswordDto() {
	    }

	    public ResetPasswordDto(String iduser, String password) {
	        this.iduser = iduser;
	        this.password = password;
	    }

	    public String getIduser() {
	        return iduser;
	    }

	    public void setIduser(String iduser) {
	        this.iduser = iduser;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

}
