package com.freestack.evaluation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UberDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private boolean available;
    private String firstname;
    private String lastname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UberDriver(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UberDriver() {
    }
}
