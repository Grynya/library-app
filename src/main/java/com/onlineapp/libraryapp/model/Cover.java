package com.onlineapp.libraryapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Covers")
public class Cover {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private String name;


    public Cover(String name) {
        this.name = name;
    }

    public Cover() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
