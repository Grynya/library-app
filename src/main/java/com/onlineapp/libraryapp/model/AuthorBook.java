package com.onlineApp.libraryApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "authors_books")
public class AuthorBook {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public AuthorBook(Integer id_author, Integer id_book) {
        this.id_author = id_author;
        this.id_book = id_book;
    }

    public AuthorBook() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    private Integer id_author;
    private Integer id_book;

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "id=" + id +
                ", id_author=" + id_author +
                ", id_book=" + id_book +
                '}';
    }
}
