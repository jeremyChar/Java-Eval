package com.freestack.evaluation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UberUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fisrtname;
    private String lastname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFisrtname() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname = fisrtname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UberUser(String fisrtname, String lastname) {
        this.fisrtname = fisrtname;
        this.lastname = lastname;
    }

    public UberUser() {
    }
}
