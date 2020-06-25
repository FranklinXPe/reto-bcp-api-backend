package com.bcp.tipocambio.dto.response;

import java.util.List;

public class JwtResponse {

    private String token;
    private static String TYPE= "Bearer";

    private String username;

    private List<String> roles;

    public JwtResponse(String accessToken,  String username,  List<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public static void setTYPE(String TYPE) {
        JwtResponse.TYPE = TYPE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
