package com.janurb.awesomeinterviewmanager.interview;

import com.janurb.awesomeinterviewmanager.storage.DatabaseAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InterviewHandler {

    private final DatabaseAdapter database;

    public InterviewHandler(DatabaseAdapter database) {
        this.database = database;
    }


    public List<Interview> loadAllInterviews() {
        return database.findAll();
    }

    public List<Interview> loadInterviewsFromToday() {
        return database.findAll()
            .stream()
            .filter(interview -> Objects.nonNull(interview.getStart()))
            .filter(interview -> interview.getStart().toLocalDate().isEqual(LocalDate.now()))
            .collect(Collectors.toList());
    }

}
