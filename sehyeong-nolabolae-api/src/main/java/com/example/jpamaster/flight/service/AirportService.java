package com.example.jpamaster.flight.service;

import com.example.jpamaster.flight.domain.entity.Airline;
import com.example.jpamaster.flight.domain.entity.Airport;
import com.example.jpamaster.flight.domain.entity.AvailableAirline;
import com.example.jpamaster.flight.domain.repository.AirlineRepository;
import com.example.jpamaster.flight.domain.repository.AirportRepository;
import com.example.jpamaster.flight.domain.repository.AvailableAirlineRepository;
import com.example.jpamaster.flight.web.dto.req.KeywordSearchConditionDto;
import com.example.jpamaster.flight.web.dto.req.RegisterAvailableAirlineRequestDto;
import com.example.jpamaster.flight.web.dto.res.AirportDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final AvailableAirlineRepository availableAirlineRepository;

    @Transactional(readOnly = true)
    public List<AirportDto> getAirportBySearchCondition(KeywordSearchConditionDto airportSearchConditionDto) {
        List<Airport> top3Airport = airportRepository.findTop3BySearchCondition(airportSearchConditionDto.getKeyword(), PageRequest.of(0, 3));
        /**
         * model mapper 의 속성들을 설정해주지 않는 경우 기본적으로 setter 매핑
         */
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper
//                .getConfiguration()
//                .setDeepCopyEnabled(true)
//                .setMatchingStrategy(MatchingStrategies.STRICT)
//                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
//                .setSkipNullEnabled(true)
//                .setFieldMatchingEnabled(true);


        return top3Airport.stream()
                .map(airport -> modelMapper.map(airport, AirportDto.class))
                .collect(Collectors.toList());
    }

    // TODO 취항사 등록 검증 처리 필요
    @Transactional
    public void registerAvailableAirline (Long airportSeq, RegisterAvailableAirlineRequestDto dto) {
        Optional<Airport> optionalAirport = airportRepository.findByAirportSeq(airportSeq);
        Optional<Airline> optionalAirline = airlineRepository.findById(dto.getAirlineSeq());

        if (optionalAirport.isPresent() && optionalAirline.isPresent()) {
            Airport airport = optionalAirport.get();
            Airline airline = optionalAirline.get();

            AvailableAirline availableAirline = AvailableAirline.builder()
                    .airline(airline)
                    .airport(airport)
                    .availableFrom(dto.getAvailableFrom())
                    .availableTo(dto.getAvailableTo())
                    .build();

            availableAirlineRepository.save(availableAirline);
        }
    }
}
