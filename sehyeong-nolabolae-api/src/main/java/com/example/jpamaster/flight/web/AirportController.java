package com.example.jpamaster.flight.web;

import com.example.jpamaster.config.security.AuthenticatedPrincipal;
import com.example.jpamaster.config.security.oauth2.CustomUserPrincipal;
import com.example.jpamaster.flight.service.AirportService;
import com.example.jpamaster.flight.web.dto.req.KeywordSearchConditionDto;
import com.example.jpamaster.flight.web.dto.req.RegisterAvailableAirlineRequestDto;
import com.example.jpamaster.flight.web.dto.res.AirportDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/airport")
@RequiredArgsConstructor
@RestController
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDto> getAirportBySearchCondition(
        @AuthenticatedPrincipal CustomUserPrincipal customUserPrincipal,
            KeywordSearchConditionDto airportSearchConditionDto
    ) {
        return airportService.getAirportBySearchCondition(airportSearchConditionDto);
    }

    @PostMapping(path = "/{airportSeq}")
    public void registerAvailableAirline (
            @PathVariable("airportSeq") Long airportSeq,
            @RequestBody  RegisterAvailableAirlineRequestDto dto
    ) {
        airportService.registerAvailableAirline(airportSeq, dto);
    }

}
