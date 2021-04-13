package com.onlineapp.libraryapp.model.request;

public class AddAuthorRequest {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String lang;

    public AddAuthorRequest(String firstName, String lastName, String patronymic, String lang) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.lang = lang;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLang() {
        return lang;
    }
}
