package com.hackerrank.github.business;

import com.hackerrank.github.comparator.ActorEventStreakComparator;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * All the business rules for this system should be concetrates here. All the public methods should return a DTO (Data tranfer object) to be used on the controller
 */
@Component
public class GitEventBusiness {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RepoRepository repoRepository;

    @Transactional
    public void deleteAllEvents(){
        eventRepository.deleteAll();
        actorRepository.deleteAll();
    }

    /**
     * Return all the Events on the database
     * @return A List of all Events
     */
    public List<GitEventDto> getAllEvents(){
        List<Event> listOfEvent = eventRepository.findAll();
        return listOfEvent.stream().map(this::convertGitEvent).collect(Collectors.toList());
    }

    /**
     * Receive a Dto and creates an Event, Actor and Repository if it's necessary
     * @param dto
     * @return The created Object
     */
    public GitEventDto createEvent(GitEventDto dto){
        final Event entity = eventRepository.save(EventConverter.convertDtoToEntity(dto));
        return EventConverter.convertEntityToDto(entity);
    }

    /**
     * Return a List of All events of an Actor if present otherwise trhows an Exception
     *
     * @param actorId
     * @return List of All events of an Actor.
     * @throws Exception if there is no Event for that actor
     */
    public List<GitEventDto> getAllEventByActorId(Long actorId) throws Exception{
        final List<Event> events = eventRepository.findAllByActor_Id(actorId);
        if(events == null)
            throw new Exception("No entity found");
        List<GitEventDto> eventsDtos = events.stream().map(this::convertGitEvent).collect(Collectors.toList());
        if(events.isEmpty())
            throw new Exception("No entity found");
        return eventsDtos;
    }

    /**
     * Updates the Avatar Url of an actor. If the object dto try to update another field rather than Avatar throws an AvatarUpdateExecption.
     *
     * @param actorDto
     * @return A copy of the new Actor
     * @throws AvatarUpdateException If another field will be updated
     * @throws NoEntityFoundException In case there is no Entity for that ID on the database
     * @throws Exception
     */
    public ActorDto updateActorAvatar(ActorDto actorDto) throws AvatarUpdateException, NoEntityFoundException, Exception {
        Actor actorEntity = actorRepository.findOne(actorDto.getId());
        if(actorEntity == null){
            throw new NoEntityFoundException();
        }
        if(actorDto.getLogin()!= null && !actorEntity.getLogin().equalsIgnoreCase(actorDto.getLogin())){
            throw new AvatarUpdateException();
        }
        actorEntity.setAvatar(actorDto.getAvatarUrl());
        return ActorConverter.convertToDto(actorRepository.save(actorEntity));
    }

    /**
     * Return a list of Actors sorted by the total number of Events that he has in descending order.
     * @return Return a list of Actors sorted by the total number of Events that he has in descending order.
     */
    public List<ActorDto> findAllSortedByEventQuantity(){
        List<Actor> resultEntity = actorRepository.findAll();
        final List<Actor> sortedList = resultEntity.stream().sorted(new ActorEventQuantityComparator()).collect(Collectors.toList());
        return sortedList.stream().map(this::convertActor).collect(Collectors.toList());
    }

    /**
     * Sort all Actors by the maximum number of consecutive days of Events.
     *
     * @return A sorted List of Actors by streak
     */
    public List<ActorDto> findAllSortedByEventStreak() {
        List<Actor> resultEntity = actorRepository.findAll();
        final List<Actor> actorListWithStreak = new ArrayList<>();
        resultEntity.
                stream().
                forEach(actor -> actorListWithStreak.add(ActorStreakCalculation.setMaximumStreakAndLatestEventDate(actor)));

        final List<ActorDto> resultDto =
                actorListWithStreak.
                        stream().
                        sorted(new ActorEventStreakComparator()).
                        map(this::convertActor).collect(Collectors.toList());
        return resultDto;
    }

    private ActorDto convertActor(Actor actor){
        return ActorConverter.convertToDto(actor);
    }

    private GitEventDto convertGitEvent(Event gitEvent){
        return EventConverter.convertEntityToDto(gitEvent);
    }
}