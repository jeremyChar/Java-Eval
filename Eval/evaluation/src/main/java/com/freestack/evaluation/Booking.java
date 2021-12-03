package com.freestack.evaluation;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Instant endOfTheBooking;
    private  Integer score;
    private Instant startOftheBooking;

    @ManyToOne
    private UberDriver driver;
    @ManyToOne
    private UberUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getEndOfTheBooking() {
        return endOfTheBooking;
    }

    public void setEndOfTheBooking(Instant endOfTheBooking) {
        this.endOfTheBooking = endOfTheBooking;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Instant getStartOftheBooking() {
        return startOftheBooking;
    }

    public void setStartOftheBooking(Instant startOftheBooking) {
        this.startOftheBooking = startOftheBooking;
    }

    public UberDriver getDriver() {
        return driver;
    }

    public void setDriver(UberDriver driver) {
        this.driver = driver;
    }

    public UberUser getUser() {
        return user;
    }

    public void setUser(UberUser user) {
        this.user = user;
    }
}
