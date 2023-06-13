package com.arkinefed.luminous.data.account;

import com.arkinefed.luminous.model.User;

import java.time.LocalDateTime;

public class UserInformationAdmin extends UserInformation {
    private String password;

    public UserInformationAdmin() {
    }

    public UserInformationAdmin(long id,
                                String username,
                                String password,
                                User.Role role,
                                String firstName,
                                String lastName,
                                LocalDateTime registerDate) {
        super(id, username, role, firstName, lastName, registerDate, 0);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
