package com.hackerrank.github.dto;

import java.sql.Timestamp;

public class GitEventDto {
    private Long id;
    private String type;
    private ActorDto actor;
    private RepoDto repo;
    private Timestamp createdAt;

    public GitEventDto() {
    }

    public GitEventDto(Long id, String type, ActorDto actor, RepoDto repo, Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

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

    public ActorDto getActor() {
        return actor;
    }

    public void setActor(ActorDto actor) {
        this.actor = actor;
    }

    public RepoDto getRepo() {
        return repo;
    }

    public void setRepo(RepoDto repo) {
        this.repo = repo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
