package com.hung.albumphoto.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;

    private String type = "Bearer";

    public LoginResponse(String jwttoken) {

        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
