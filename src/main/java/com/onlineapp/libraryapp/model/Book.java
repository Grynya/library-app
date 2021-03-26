package com.onlineapp.libraryapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "id_publish_year")
    private int publishYear;
    @Column(name = "id_edition")
    private int edition;
    @Column(name = "id_cover")
    private int cover;
    @Column(name = "isbn")
    private String isbn;

    public Book(String name,  int publishYear, int edition, int cover, String isbn) {
        this.name = name;
        this.publishYear = publishYear;
        this.edition = edition;
        this.cover = cover;
        this.isbn = isbn;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", edition=" + edition +
                ", cover=" + cover +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
