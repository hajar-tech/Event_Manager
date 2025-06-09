package com.example.EventManager.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="reservations")
public class Reservation {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String userName;
    int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;


    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
