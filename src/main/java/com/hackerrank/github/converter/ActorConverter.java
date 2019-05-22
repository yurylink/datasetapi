package com.hackerrank.github.converter;

import com.hackerrank.github.dto.ActorDto;
import com.hackerrank.github.model.Actor;

public class ActorConverter {

    public static ActorDto convertToDto(Actor actorEntity){
        final boolean targetIdNull = actorEntity == null;
//        final boolean listOfEventsIsNull = targetIdNull ? false : actorEntity.getListOfEvents() == null;

        final Long id = targetIdNull ? null : actorEntity.getId();
        final String login = targetIdNull ? null : actorEntity.getLogin();
        final String avatar = targetIdNull ? null : actorEntity.getAvatar();
//        final List<Event> listOfEvents = listOfEventsIsNull ? null : actorEntity.getListOfEvents().stream().forEach(event -> );
        return new ActorDto(id, login, avatar);
    }

    public static Actor convertToEntity(ActorDto dto){
        final boolean targetIdNull = dto == null;
//        final boolean listOfEventsIsNull = targetIdNull ? false : dto.getgetListOfEvents() == null;

        final Long id = targetIdNull ? null : dto.getId();
        final String login = targetIdNull ? null : dto.getLogin();
        final String avatar = targetIdNull ? null : dto.getAvatarUrl();
//        final List<Event> listOfEvents = listOfEventsIsNull ? null : actorEntity.getListOfEvents().stream().forEach(event -> );
        return new Actor(id, login, avatar, null);
    }
}