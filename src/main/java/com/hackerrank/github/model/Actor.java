package com.hackerrank.github.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Actor {
    @Id
    private Long id;
    private String login;
    private String avatar;

    @OneToMany(mappedBy = "actor")
    private List<Event> listOfEvents;

    public Actor() {
    }

    public Actor(Long id, String login, String avatar) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
    }

    public Actor(Long id, String login, String avatar, List<Event> listOfEvents) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
        this.listOfEvents = listOfEvents;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }
}