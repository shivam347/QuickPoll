package com.polling.votingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.polling.votingapp.model.OptionVote;
import com.polling.votingapp.model.Poll;
import com.polling.votingapp.repository.PollRepository;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    /* Method used to create the poll */
    public Poll createPoll(Poll poll) {
        if (poll == null) {
            throw new IllegalArgumentException("Poll cannot be null");
        }
        return pollRepository.save(poll);
    }

    /* This will call poll repository and find all the polls in the database */
    public List<Poll> getAllPolls() {

        return pollRepository.findAll();
    }

    /* This will call the repository and return the poll by finding it through id */
    public Optional<Poll> getPollById(Long id) {

        return pollRepository.findById(id);
    }

    /*
     * Method to do vote 
     */
    public void getVote(Long pollId, int optionIndex) {

        // Get poll by id from db
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("poll is not found"));

        // Get all options
        List<OptionVote> options = poll.getOptions();

        // error for optionIndex
        if(optionIndex < 0 || optionIndex > options.size()){
            throw new IllegalArgumentException("Invalid OptionIndex");
        }

        // get selected option
        OptionVote selectedOption = options.get(optionIndex);

        // increment the count of selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        pollRepository.save(poll);

    }
}

