package com.hackerrank.github.controller;

import com.hackerrank.github.business.GitEventBusiness;
import com.hackerrank.github.dto.GitEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubApiRestController {

    @Autowired
    private GitEventBusiness business;

    @GetMapping("/erase")
    public ResponseEntity eraseAll(){
        business.deleteAllEvents();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/event")
    public ResponseEntity getall(){
        List<GitEventDto> result = business.getAllEvents();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/event")
    public ResponseEntity createEvent(@RequestBody GitEventDto dto){
        GitEventDto responseDto = business.createEvent(dto);
        return ResponseEntity.ok(responseDto);
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
}