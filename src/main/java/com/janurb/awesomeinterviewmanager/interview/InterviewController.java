package com.janurb.awesomeinterviewmanager.interview;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/interviews")
public class InterviewController {

    private final InterviewHandler interviewHandler;

    public InterviewController(InterviewHandler interviewHandler) {
        this.interviewHandler = interviewHandler;
    }

    @GetMapping
    public List<Interview> listAllInterviews() {
        return interviewHandler.loadAllInterviews();
    }

    @GetMapping("/today")
    public List<Interview> listInterviewsOfToday() {
        return interviewHandler.loadInterviewsFromToday();
    }


}
