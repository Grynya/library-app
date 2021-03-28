package com.onlineapp.libraryapp.model.request;

public class UpdateAuthorRequest {
    private final String oldFirstName;
    private final String oldLastName;
    private final String oldPatronymic;
    private final String newLastName;
    private final String newFirstName;
    private final String newPatronymic;
    private final String lang;

    public UpdateAuthorRequest(String oldFirstName, String oldLastName, String oldPatronymic, String newLastName, String newFirstName, String newPatronymic, String lang) {
        this.oldFirstName = oldFirstName;
        this.oldLastName = oldLastName;
        this.oldPatronymic = oldPatronymic;
        this.newLastName = newLastName;
        this.newFirstName = newFirstName;
        this.newPatronymic = newPatronymic;
        this.lang = lang;
    }

    public String getOldFirstName() {
        return oldFirstName;
    }

    public String getOldLastName() {
        return oldLastName;
    }

    public String getOldPatronymic() {
        return oldPatronymic;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewPatronymic() {
        return newPatronymic;
    }

    public String getLang() {
        return lang;
    }
}
