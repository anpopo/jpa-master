package com.example.jpamaster.flight.service;

import com.example.jpamaster.common.exception.JpaMasterBadRequest;
import com.example.jpamaster.common.exception.JpaMasterNotFoundException;
import com.example.jpamaster.flight.domain.entity.Airplane;
import com.example.jpamaster.flight.domain.entity.AirplaneSeatType;
import com.example.jpamaster.flight.domain.entity.Airport;
import com.example.jpamaster.flight.domain.repository.AirplaneRepository;
import com.example.jpamaster.flight.domain.repository.AirportRepository;
import com.example.jpamaster.flight.domain.repository.AvailableAirlineRepository;
import com.example.jpamaster.flight.util.FlightUtils;
import com.example.jpamaster.flight.web.dto.req.AirScheduleSeatRequestDto;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FlightValidationService {

    private final AirportRepository airportRepository;
    private final AirplaneRepository airplaneRepository;
    private final AvailableAirlineRepository availableAirlineRepository;

    void availableAirlineValidation(Airplane airplane, Airport fromAirport, Airport toAirport) {
        if (availableAirlineRepository.countByAirline_AirlineSeqAndAirport_AirportSeqIn(
            airplane.getAirline().getAirlineSeq(), List.of(fromAirport.getAirportSeq(), toAirport.getAirportSeq()))
            < 2) {
            throw new JpaMasterBadRequest("취항 하려는 공항 정보가 잘못되었습니다.");
        }
    }

    Airport airScheduleAirportValidation(Long airportSeq) {
        Optional<Airport> optionalAirport = airportRepository.findByAirportSeq(airportSeq);
        if (optionalAirport.isEmpty()) {
            throw new JpaMasterNotFoundException("존재하지 않는 공항입니다.");
        }
        return optionalAirport.get();
    }

    Airplane airScheduleAirplaneValidation(Long airplaneSeq) {
        Optional<Airplane> optionalAirplane = airplaneRepository.findByAirplaneSeqAndAvailableIsTrue(airplaneSeq);

        if (optionalAirplane.isEmpty()) {
            throw new JpaMasterNotFoundException("존재하지 않는 항공기입니다.");
        }

        return optionalAirplane.get();
    }

    void airplaneSeatValidation(Set<AirplaneSeatType> airplaneSeatTypes,
        Set<AirScheduleSeatRequestDto> airScheduleSeatRequestDtos) {
        for (AirScheduleSeatRequestDto dto : airScheduleSeatRequestDtos) {
            AirplaneSeatType seatType = airplaneSeatTypes.stream()
                .filter(airplaneSeatType -> airplaneSeatType.getSeatType() == dto.getSeatType())
                .findFirst()
                .orElseThrow(() -> new JpaMasterBadRequest(
                    String.format("%s - 해당 좌석 타입이 존재하지 않습니다.", dto.getSeatType().getKrName())));

            if (seatType.getAvailableSeatCount() < dto.getAvailableSeatCount()) {
                throw new JpaMasterBadRequest(
                    String.format("최대 좌석수를 초과했습니다. 최대 좌석수 - %d.", seatType.getAvailableSeatCount()));
            }
        }
    }

    public void takeOffTimeValidation(String zoneId, String expectedTakeoffDate, String expectedTakeoffTime) {
        LocalDateTime takeOffDate = FlightUtils.toLocalDateTime(expectedTakeoffDate, expectedTakeoffTime);
        LocalDateTime now = ZonedDateTime.now(ZoneId.of(zoneId)).toLocalDateTime();

        if (takeOffDate.isBefore(now)) {
            throw new JpaMasterBadRequest("출발 시간이 현재 시간보다 이전입니다.");
        }

    }
}
