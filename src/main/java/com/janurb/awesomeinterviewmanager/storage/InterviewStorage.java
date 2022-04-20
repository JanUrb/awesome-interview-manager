package com.janurb.awesomeinterviewmanager.storage;

import com.janurb.awesomeinterviewmanager.interview.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewStorage {

    List<Interview> findAll();

    Optional<Interview> findById();

}
