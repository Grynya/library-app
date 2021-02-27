package com.onlineApp.libraryApp.model;

import javax.persistence.*;

@Entity(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;

    @Column(
            name = "first_name",
            length = 50
    )
    private  String firstName;
    @Column(
            name = "last_name",
            length = 50
    )
    private  String lastName;
    @Column(
            name = "Patronymic",
            length = 50
    )
    private  String patronymic;

    public Author() {

    }

    public Author(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
