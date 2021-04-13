package com.onlineapp.libraryapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Editions")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int value;

    public Edition(Integer value) {
        this.value = value;
    }

    public Edition() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
