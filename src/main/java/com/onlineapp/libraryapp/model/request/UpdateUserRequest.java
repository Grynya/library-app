package com.onlineapp.libraryapp.model.request;

public class UpdateUserRequest {
    private final String oldFirstName;
    private final String oldLastName;
    private final String oldLogin;
    private final String oldPassword;
    private final String newFirstName;
    private final String newLastName;
    private final String newLogin;
    private final String newPassword;
    private final String lang;

    public UpdateUserRequest(String oldFirstName, String oldLastName, String oldLogin, String oldPassword, String newFirstName, String newLastName, String newLogin, String newPassword, String lang) {
        this.oldFirstName = oldFirstName;
        this.oldLastName = oldLastName;
        this.oldLogin = oldLogin;
        this.oldPassword = oldPassword;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newLogin = newLogin;
        this.newPassword = newPassword;
        this.lang = lang;
    }

    public String getOldFirstName() {
        return oldFirstName;
    }

    public String getOldLastName() {
        return oldLastName;
    }

    public String getOldLogin() {
        return oldLogin;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getLang() {
        return lang;
    }
}
