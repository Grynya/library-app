package com.onlineapp.libraryapp.model.request;

public class AddBookRequest {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String name;
    private final String publishYear;
    private final String edition;
    private final String cover;
    private final String isbn;
    private final String lang;

    public AddBookRequest(String firstName, String lastName, String patronymic, String name, String publishYear, String edition, String cover, String isbn, String lang) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.name = name;
        this.publishYear = publishYear;
        this.edition = edition;
        this.cover = cover;
        this.isbn = isbn;
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

    public String getName() {
        return name;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public String getEdition() {
        return edition;
    }

    public String getCover() {
        return cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLang() {
        return lang;
    }
}
