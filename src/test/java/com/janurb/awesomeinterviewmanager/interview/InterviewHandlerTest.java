package com.janurb.awesomeinterviewmanager.interview;

import com.janurb.awesomeinterviewmanager.storage.DatabaseAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterviewHandlerTest {


    @Test
    void loads_all_interview_from_db() {
        var dbMock = mock(DatabaseAdapter.class);
        var uuid = UUID.randomUUID();
        List<Interview> expected = List.of(new Interview(uuid, null, null, null, null));
        when(dbMock.findAll()).thenReturn(expected);
        var sut = new InterviewHandler(dbMock);

        var actual = sut.loadAllInterviews();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void loads_interviews_from_today() {
        var dbMock = mock(DatabaseAdapter.class);
        var uuid = UUID.randomUUID();
        List<Interview> expected = List.of(new Interview(uuid, null, null, LocalDateTime.now(), null));
        when(dbMock.findAll()).thenReturn(expected);
        var sut = new InterviewHandler(dbMock);

        var actual = sut.loadInterviewsFromToday();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void filter_null_starttimes_when_loading_interviews_from_today() {
        var dbMock = mock(DatabaseAdapter.class);
        var uuid = UUID.randomUUID();
        when(dbMock.findAll()).thenReturn(List.of(new Interview(uuid, null, null, null, null)));
        var sut = new InterviewHandler(dbMock);

        var actual = sut.loadInterviewsFromToday();

        assertThat(actual).isEqualTo(Collections.emptyList());
    }

}