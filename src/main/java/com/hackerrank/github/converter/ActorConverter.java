package com.hackerrank.github.converter;

import com.hackerrank.github.dto.ActorDto;
import com.hackerrank.github.model.Actor;

/**
 * Class responsible for convert the Actor Data Transfer Object in a Entity and Entity in a DTO
 */
public class ActorConverter {

    public static ActorDto convertToDto(Actor actorEntity){
        final boolean targetIdNull = actorEntity == null;

        final Long id = targetIdNull ? null : actorEntity.getId();
        final String login = targetIdNull ? null : actorEntity.getLogin();
        final String avatar = targetIdNull ? null : actorEntity.getAvatar();
        return new ActorDto(id, login, avatar);
    }

    public static Actor convertToEntity(ActorDto dto){
        final boolean targetIdNull = dto == null;

        final Long id = targetIdNull ? null : dto.getId();
        final String login = targetIdNull ? null : dto.getLogin();
        final String avatar = targetIdNull ? null : dto.getAvatarUrl();

        return new Actor(id, login, avatar);
    }
}