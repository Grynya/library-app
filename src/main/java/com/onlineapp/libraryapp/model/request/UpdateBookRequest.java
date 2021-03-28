package com.onlineapp.libraryapp.model.request;

public class UpdateBookRequest {
    private final String oldName;
    private final String oldIsbn;
    private final String newFirstName;
    private final String newLastName;
    private final String newPatronymic;
    private final String newName;
    private final String newPublishYear;
    private final String newEdition;
    private final String newCover;
    private final String newIsbn;
    private final String lang;

    public UpdateBookRequest(String oldName, String oldIsbn, String newFirstName, String newLastName, String newPatronymic, String newName, String newPublishYear, String newEdition, String newCover, String newIsbn, String lang) {
        this.oldName = oldName;
        this.oldIsbn = oldIsbn;
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newPatronymic = newPatronymic;
        this.newName = newName;
        this.newPublishYear = newPublishYear;
        this.newEdition = newEdition;
        this.newCover = newCover;
        this.newIsbn = newIsbn;
        this.lang = lang;
    }

    public String getOldName() {
        return oldName;
    }

    public String getOldIsbn() {
        return oldIsbn;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewPatronymic() {
        return newPatronymic;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewPublishYear() {
        return newPublishYear;
    }

    public String getNewEdition() {
        return newEdition;
    }

    public String getNewCover() {
        return newCover;
    }

    public String getNewIsbn() {
        return newIsbn;
    }

    public String getLang() {
        return lang;
    }
}
