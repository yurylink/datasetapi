package com.hackerrank.github.dto;

import java.io.Serializable;

/**
 * This object should be used to data only for transfer and customize the JSON names used on the controllers
 */
public class RepoDto implements Serializable {
    private static final long serialVersionUID = -975402153364620846L;

    private Long id;
    private String name;
    private String url;

    public RepoDto() {
    }

    public RepoDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
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
}
