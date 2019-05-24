package com.hackerrank.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * This object should be used to data only for transfer and customize the JSON names used on the controllers
 */
public class ActorDto implements Serializable {
    private static final long serialVersionUID = 8731627752955965514L;

    private Long id;
    private String login;
    @JsonProperty("avatar_url")
    private String avatarUrl;

    public ActorDto() {
    }

    public ActorDto(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}