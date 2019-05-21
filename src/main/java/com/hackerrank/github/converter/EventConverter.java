package com.hackerrank.github.converter;

import com.hackerrank.github.dto.ActorDto;
import com.hackerrank.github.dto.GitEventDto;
import com.hackerrank.github.dto.RepoDto;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;

import java.sql.Timestamp;
import java.util.Date;

public class EventConverter {

    public static GitEventDto convertEntityToDto(Event event){
        final boolean isTargerNull = event == null;

        final Long id = isTargerNull ? null : event.getId();
        final String type = isTargerNull ? null : event.getType();
        final ActorDto actor = isTargerNull ? null : ActorConverter.convertToDto(event.getActor());
        final RepoDto repo = isTargerNull ? null : RepoConverter.convertEntityToDto(event.getRepo());
        final Date createdAt = isTargerNull ? null : event.getCreatedAt();

        return new GitEventDto(id, type, actor, repo, createdAt);
    }

    public static Event convertEntityToEntity(GitEventDto dto){
        final boolean isTargerNull = dto == null;

        final Long id = isTargerNull ? null : dto.getId();
        final String type = isTargerNull ? null : dto.getType();
        final Actor actor = isTargerNull ? null : ActorConverter.convertToEntity(dto.getActor());
        final Repo repo = isTargerNull ? null : RepoConverter.convertEntityToEntity(dto.getRepo());
//        final Timestamp createdAt = isTargerNull ? null : dto.getCreatedAt() == null ? new Timestamp(new Date().getTime()) : dto.getCreatedAt();
        final Date createdAt = isTargerNull ? null : dto.getCreatedAt() == null ? new Date() : dto.getCreatedAt();

        return new Event(id, type, actor, repo, createdAt);
    }
}
