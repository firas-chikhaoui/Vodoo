package com.vodoo.vodooapiadmin.dto;

public class LoginDto {


    private String login;
    private String pwd;


    public LoginDto() {
		super();
	}

	public LoginDto(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
