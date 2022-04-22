package com.janurb.awesomeinterviewmanager.services;

import com.janurb.awesomeinterviewmanager.interview.Interview;
import com.janurb.awesomeinterviewmanager.storage.DatabaseAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.List.of;

@Service
public class InterviewService {

    private final DatabaseAdapter database;


    public InterviewService(DatabaseAdapter database) {
        this.database = database;
    }

    public Optional<Interview> addPartner(UUID interviewId, String partner) {
        var interviewOpt = database.findById(interviewId);
        if (interviewOpt.isPresent()) {
            var interview = interviewOpt.get();

            if (interviewIsExpired(interview)) {
                return Optional.empty();
            }

            interview.setInterviewPartner(partner);
            var storedInterview = database.save(interview);
            return Optional.of(storedInterview);
        }
        return Optional.empty();
    }

    public List<Interview> replacePartner(String currentPartner, String replacement) {
        if (currentPartner.equals(replacement)) {
            return of();
        }

        var interviews = database.findAll();
        var filteredInterviews = new ArrayList<Interview>();

        for (var interview : interviews) {
            if (interviewIsExpired(interview)) {
                continue;
            }
            if (interview.getInterviewPartner().equals(currentPartner)) {
                filteredInterviews.add(interview);
            }
        }

        var storedInterviews = new ArrayList<Interview>();

        for (var filteredInterview : filteredInterviews) {
            filteredInterview.setInterviewPartner(replacement);
            var stored = database.save(filteredInterview);
            storedInterviews.add(stored);
        }

        return storedInterviews;
    }

    private boolean interviewIsExpired(Interview interview) {
        var isExpired = interview.getStart().toLocalDate().isBefore(LocalDate.now());
        return isExpired;
    }
}
