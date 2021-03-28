package com.onlineapp.libraryapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "authors_books")
public class AuthorBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public AuthorBook(Integer idAuthor, Integer idBook) {
        this.idAuthor = idAuthor;
        this.idBook = idBook;
    }

    public AuthorBook() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private Integer idAuthor;
    private Integer idBook;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int id_author) {
        this.idAuthor = id_author;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int id_book) {
        this.idBook = id_book;
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "id=" + id +
                ", idAuthor=" + idAuthor +
                ", idBook=" + idBook +
                '}';
    }
}
