package com.example.jpamaster.flight.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.example.jpamaster.flight.domain.entity.Airline;
import com.example.jpamaster.flight.domain.entity.Airport;
import com.example.jpamaster.flight.domain.repository.AirlineRepository;
import com.example.jpamaster.flight.domain.repository.AirportRepository;
import com.example.jpamaster.flight.domain.repository.AvailableAirlineRepository;
import com.example.jpamaster.flight.fixture.Fixture;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AvailableAirlineRepository availableAirlineRepository;

    @Test
    void 취항사_등록() {
        // given
        Airport airport = Fixture.generateAirport();
        Airline airline = Fixture.generateAirline();

        BDDMockito.given(airportRepository.findByAirportSeq(anyLong()))
                  .willReturn(Optional.of(airport));
        BDDMockito.given(airlineRepository.findById(anyLong()))
                  .willReturn(Optional.of(airline));
        BDDMockito.given(availableAirlineRepository.save(any()))
                  .willReturn(null);
        // when
        airportService.registerAvailableAirline(1L, Fixture.generateRegisterAvailableAirlineRequestDto());

        // then
        BDDMockito.verify(availableAirlineRepository, BDDMockito.times(1))
                  .save(any());
    }
}