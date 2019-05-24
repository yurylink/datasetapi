package com.hackerrank.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hackerrank.github.util.JsonDateDeserializer;
import com.hackerrank.github.util.JsonDateSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * This object should be used to data only for transfer and customize the JSON names used on the controllers
 */
public class GitEventDto implements Serializable {
    private static final long serialVersionUID = 7045372686030912557L;

    private Long id;
    private String type;
    private ActorDto actor;
    private RepoDto repo;
    @JsonProperty("created_at")
    @JsonSerialize(using = JsonDateSerialize.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date createdAt;

    public GitEventDto() {
    }

    public GitEventDto(Long id, String type, ActorDto actor, RepoDto repo, Date createdAt) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ActorDto getActor() {
        return actor;
    }

    public RepoDto getRepo() {
        return repo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}