package com.hackerrank.github.converter;

import com.hackerrank.github.dto.RepoDto;
import com.hackerrank.github.model.Repo;

public class RepoConverter {

    public static RepoDto convertEntityToDto(Repo repo){
        final boolean targetIsNull = repo == null;

        final Long id = targetIsNull ? null : repo.getId();
        final String name = targetIsNull ? null : repo.getName();
        final String url = targetIsNull ? null : repo.getUrl();

        return new RepoDto(id, name, url);
    }

    public static Repo convertEntityToEntity(RepoDto dto){
        final boolean targetIsNull = dto == null;

        final Long id = targetIsNull ? null : dto.getId();
        final String name = targetIsNull ? null : dto.getName();
        final String url = targetIsNull ? null : dto.getUrl();

        return new Repo(id, name, url);
    }
}