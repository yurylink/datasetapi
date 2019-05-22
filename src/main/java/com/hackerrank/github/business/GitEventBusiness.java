package com.hackerrank.github.business;

import com.hackerrank.github.converter.EventConverter;
import com.hackerrank.github.dto.GitEventDto;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}