package com.polling.votingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.polling.votingapp.dto.Vote;
import com.polling.votingapp.model.Poll;
import com.polling.votingapp.services.PollService;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    @Autowired
    PollService pollservice;

    /* Method to create poll */
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){

        return pollservice.createPoll(poll);
    }


    /* Method to fetch all the polls */
    @GetMapping
    public List<Poll> getAllPolls(){
        
        return pollservice.getAllPolls();
    }

    /* Method to get poll by id */
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id){

        return pollservice.getPollById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
         
        pollservice.getVote(vote.getPollId(), vote.getOptionIndex());
    }




    
}
