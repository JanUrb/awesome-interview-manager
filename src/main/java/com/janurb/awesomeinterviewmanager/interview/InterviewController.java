package com.janurb.awesomeinterviewmanager.interview;

import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}/partner")
    public Interview addPartner(@PathVariable("id") String interviewId, @RequestParam("partner") String partner) {
        return interviewHandler.addPartner(interviewId, partner);
    }

    @PostMapping("/massreplacement")
    public List<Interview> replaceAll(@RequestParam("currentPartner") String currentPartner, @RequestParam("replacement") String replacement) {
        return interviewHandler.replacePartner(currentPartner, replacement);
    }
}
