package com.example.jpamaster.flight.domain.repository;

import com.example.jpamaster.flight.domain.entity.AvailableAirline;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableAirlineRepository extends JpaRepository<AvailableAirline, Long> {
    long countByAirline_AirlineSeqAndAirport_AirportSeqIn(Long airlineSeq, List<Long> airportSeqs);
}
