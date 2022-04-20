package com.janurb.awesomeinterviewmanager.storage;


import com.janurb.awesomeinterviewmanager.interview.Interview;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatabaseAdapter implements InterviewStorage {

    //hardcoded test values -> should be stored in a database
    private final List<Interview> interviews = List.of(new Interview(UUID.randomUUID(), "Jan", "Tamina", LocalDateTime.now(), 60));


    @Override
    public List<Interview> findAll() {
        return interviews;
    }

    @Override
    public Optional<Interview> findById() {
        return Optional.empty();
    }
}
