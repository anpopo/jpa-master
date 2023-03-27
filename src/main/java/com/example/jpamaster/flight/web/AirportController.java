package com.example.jpamaster.flight.web;

import com.example.jpamaster.common.ApiResponse;
import com.example.jpamaster.common.security.AuthenticatedPrincipal;
import com.example.jpamaster.common.security.oauth2.CustomUserPrincipal;
import com.example.jpamaster.flight.service.AirportService;
import com.example.jpamaster.flight.web.dto.req.KeywordSearchConditionDto;
import com.example.jpamaster.flight.web.dto.req.RegisterAvailableAirlineRequestDto;
import com.example.jpamaster.flight.web.dto.res.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
