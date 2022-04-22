package com.janurb.awesomeinterviewmanager.interview;

import com.janurb.awesomeinterviewmanager.services.InterviewService;
import com.janurb.awesomeinterviewmanager.storage.DatabaseAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterviewHandler {

    private final DatabaseAdapter database;
    private final InterviewService interviewService;

    public InterviewHandler(DatabaseAdapter database, InterviewService interviewService) {
        this.database = database;
        this.interviewService = interviewService;
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

    public Interview addPartner(String id, String partner) {
        return interviewService.addPartner(UUID.fromString(id), partner).orElse(null);
    }

    public List<Interview> replacePartner(String currentPartner, String replacement) {
        return interviewService.replacePartner(currentPartner, replacement);
    }
}
