package com.arkinefed.luminous.data.account;

public class LoginResponse {
    private boolean logged;
    private String username;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(boolean logged) {
        this.logged = logged;
    }

    public LoginResponse(boolean logged, String username, String token) {
        this.logged = logged;
        this.username = username;
        this.token = token;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
