package com.hackerrank.github.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
public class Actor {
    @Id
    private Long id;
    private String login;
    private String avatar;

    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    private List<Event> events;

    @Transient
    private Integer maximumStreak;

    @Transient
    private Date latestEvent;

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
        this.events = listOfEvents;
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Integer getMaximumStreak() {
        return maximumStreak;
    }

    public void setMaximumStreak(Integer maximumStreak) {
        this.maximumStreak = maximumStreak;
    }

    public Date getLatestEvent() {
        return latestEvent;
    }

    public void setLatestEvent(Date latestEvent) {
        this.latestEvent = latestEvent;
    }
}