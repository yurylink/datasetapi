package com.hackerrank.github.controller;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubApiRestController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/test")
    public ResponseEntity test(){
        Actor a1 = new Actor();
        a1.setAvatar("avatart");
        a1.setLogin("Anakin.Skywalker");
        a1.setId(1L);

        actorRepository.save(a1);

        return ResponseEntity.ok().body(actorRepository.findAll());
    }
}
