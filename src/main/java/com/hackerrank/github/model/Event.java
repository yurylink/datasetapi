package com.hackerrank.github.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Event {
    @Id
    private Long id;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", insertable = true, updatable = true)
    private Actor actor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "repo_id", insertable = true, updatable = true)
    private Repo repo;

//    private Timestamp createdAt;
    private Date createdAt;

    public Event() {
    }

    public Event(Long id, String type, Actor actor, Repo repo, Date createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    //    public Event(Long id, String type, Actor actor, Repo repo, Timestamp createdAt) {
//        this.id = id;
//        this.type = type;
//        this.actor = actor;
//        this.repo = repo;
//        this.createdAt = createdAt;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }


    public Date getCreatedAt() {
        return createdAt;
    }

//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
}
