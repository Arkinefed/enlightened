package com.arkinefed.luminous.data;

public class RegisterResponse {
    private boolean registered;
    private String message;

    public RegisterResponse() {
    }

    public RegisterResponse(boolean registered, String message) {
        this.registered = registered;
        this.message = message;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
