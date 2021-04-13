package com.onlineapp.libraryapp.model.request;

public class AddUserRequest {
    private final String firstname;
    private final String lastname;
    private final String userName;
    private final String password;
    private final String lang;

    public AddUserRequest(String firstname, String lastname, String userName, String password, String lang) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userName = userName;
        this.password = password;
        this.lang = lang;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLang() {
        return lang;
    }
}
