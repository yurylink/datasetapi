package com.hackerrank.github.controller;

import com.hackerrank.github.business.GitEventBusiness;
import com.hackerrank.github.dto.ActorDto;
import com.hackerrank.github.dto.GitEventDto;
import com.hackerrank.github.exceptions.AvatarUpdateException;
import com.hackerrank.github.exceptions.NoEntityFoundException;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubApiRestController {

    @Autowired
    private GitEventBusiness business;

    @DeleteMapping("/erase")
    public ResponseEntity eraseAll(){
        business.deleteAllEvents();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events")
    public ResponseEntity getall(){
        List<GitEventDto> result = business.getAllEvents();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/events")
    public ResponseEntity createEvent(@RequestBody GitEventDto dto){
        GitEventDto responseDto = business.createEvent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/events/actors/{actorID}")
    public ResponseEntity createEvent(@PathVariable Long actorID){
        try{
            List<GitEventDto> responseList = business.getAllEventByActorId(actorID);
            return ResponseEntity.ok(responseList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/actors")
    public ResponseEntity updateActorAvatar(@RequestBody ActorDto actorDto){
        try{
            ActorDto updatedActor = business.updateActorAvatar(actorDto);
            return ResponseEntity.ok(updatedActor);
        }catch (AvatarUpdateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only avatar can be changed. Operation not Allowed.");
        }catch (NoEntityFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No actor with id" + (actorDto.getId() == null ? "[null]" : actorDto.getId()) + " found.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/actors")
    public ResponseEntity getAllActorSortedNumberOfEvents(){
        final List<ActorDto> resultList = business.findAllSortedByEventQuantity();
        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/actors/streak")
    public ResponseEntity getAllActorSortedByStreak(){
        final List<ActorDto> resultList = business.findAllSortedByEventStreak();

        final HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        return ResponseEntity.ok().headers(header).body(resultList);
    }

//    @RequestMapping(value = "/actors/streak", method = RequestMethod.GET)
//    public @ResponseBody List<ActorDto> getAllActorSortedByStreack(){
//        final List<ActorDto> resultList = business.findAllSortedByEventStreak();
//        return resultList;
//    }
}