package com.janurb.awesomeinterviewmanager.interview;

import java.time.LocalDateTime;
import java.util.UUID;

public class Interview {
    private UUID uuid;
    private String interviewPartner;
    private String candidate;
    private LocalDateTime start;
    private Integer durationInMinutes;

    public UUID getUuid() {
        return uuid;
    }

    public String getInterviewPartner() {
        return interviewPartner;
    }

    public String getCandidate() {
        return candidate;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Interview(UUID uuid, String interviewPartner, String candidate, LocalDateTime start, Integer durationInMinutes) {
        this.uuid = uuid;
        this.interviewPartner = interviewPartner;
        this.candidate = candidate;
        this.start = start;
        this.durationInMinutes = durationInMinutes;
    }
}
