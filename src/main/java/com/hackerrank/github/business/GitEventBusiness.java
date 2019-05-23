package com.hackerrank.github.business;

import com.hackerrank.github.converter.ActorConverter;
import com.hackerrank.github.converter.EventConverter;
import com.hackerrank.github.dto.ActorDto;
import com.hackerrank.github.dto.GitEventDto;
import com.hackerrank.github.exceptions.AvatarUpdateException;
import com.hackerrank.github.exceptions.NoEntityFoundException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.comparator.ActorEventQuantityComparator;
import com.hackerrank.github.util.ActorStreakCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GitEventBusiness {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepoRepository repoRepository;

    public void deleteAllEvents(){
        eventRepository.deleteAll();
    }

    public List<GitEventDto> getAllEvents(){
        List<Event> listOfEvent = eventRepository.findAll();
        return listOfEvent.stream().map(event -> EventConverter.convertEntityToDto(event)).collect(Collectors.toList());
    }

    public GitEventDto createEvent(GitEventDto dto){
        final Event entity = eventRepository.save(EventConverter.convertDtoToEntity(dto));

        return EventConverter.convertEntityToDto(entity);
    }

    public List<GitEventDto> getAllEventByActorId(Long actorId) throws Exception{
        final List<Event> events = eventRepository.findAllByActor_Id(actorId);
        if(events == null)
            throw new Exception("No entity found");
        List<GitEventDto> eventsDtos = events.stream().map(event -> EventConverter.convertEntityToDto(event)).collect(Collectors.toList());
        if(events.isEmpty())
            throw new Exception("No entity found");
        return eventsDtos;
    }

    public ActorDto updateActorAvatar(ActorDto actorDto) throws AvatarUpdateException, NoEntityFoundException, Exception {
        Actor actorEntity = actorRepository.findOne(actorDto.getId());
        if(actorEntity == null){
            throw new NoEntityFoundException();
        }
        if(actorDto.getLogin()!= null && !actorEntity.getAvatar().equalsIgnoreCase(actorDto.getLogin())){
            throw new AvatarUpdateException();
        }
        actorEntity.setAvatar(actorDto.getAvatarUrl());
        return ActorConverter.convertToDto(actorRepository.save(actorEntity));
    }

    public List<ActorDto> findAllSortedByEventQuantity(){
        final List<Actor> resultEntity = actorRepository.findAll();

        return resultEntity.
                stream().
                sorted(new ActorEventQuantityComparator()).
                map(actor -> ActorConverter.convertToDto(actor)).
                collect(Collectors.toList());
    }

    public List<ActorDto> findAllSortedByEventStreak() {
        List<Actor> resultEntity = actorRepository.findAll();
//        final List<Actor> actorListWithStreak = resultEntity.stream().map(actor -> ActorStreakCalculation.setMaximumStreakAndLatestEventDate(actor) ).collect(Collectors.toList());
        final List<Actor> actorListWithStreak = new ArrayList<>();
        resultEntity.
                stream().
                forEach(actor -> actorListWithStreak.add(ActorStreakCalculation.setMaximumStreakAndLatestEventDate(actor)));

        return actorListWithStreak.
                stream().
                sorted().
                map(actor -> ActorConverter.convertToDto(actor)).
                collect(Collectors.toList());
    }
}