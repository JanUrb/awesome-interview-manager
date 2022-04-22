package com.janurb.awesomeinterviewmanager.services;

import com.janurb.awesomeinterviewmanager.interview.Interview;
import com.janurb.awesomeinterviewmanager.storage.DatabaseAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterviewServiceTest {


    @Test
    void adds_a_partner() {
        var dbMock = mock(DatabaseAdapter.class);
        var uuid = UUID.randomUUID();
        when(dbMock.findById(uuid)).thenReturn(Optional.of(new Interview(uuid, null, null, LocalDateTime.now(), null)));
        when(dbMock.save(any())).thenAnswer(returnsFirstArg());
        var sut = new InterviewService(dbMock);

        var actual = sut.addPartner(uuid, "Jan");

        assertThat(actual).isPresent();
        assertThat(actual.get().getInterviewPartner()).isEqualTo("Jan");
    }

    @Test
    void replaces_a_partner() {
        var dbMock = mock(DatabaseAdapter.class);
        var uuid = UUID.randomUUID();
        when(dbMock.findAll()).thenReturn(List.of(new Interview(uuid, "Jan", null, LocalDateTime.now(), null)));
        when(dbMock.save(any())).thenAnswer(returnsFirstArg());
        var sut = new InterviewService(dbMock);

        var actual = sut.replacePartner("Jan", "Maik");

        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getInterviewPartner()).isEqualTo("Maik");
    }

}