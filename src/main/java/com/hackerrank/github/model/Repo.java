package com.hackerrank.github.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Repo {
    @Id
    private Long id;
    private String name;
    private String url;

    @OneToMany(mappedBy = "repo", fetch = FetchType.LAZY)
    private List<Event> listOfEvents;

    public Repo() {
    }

    public Repo(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Repo(Long id, String name, String url, List<Event> listOfEvents) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.listOfEvents = listOfEvents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }
}
