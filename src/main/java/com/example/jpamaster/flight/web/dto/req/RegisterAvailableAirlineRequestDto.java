package com.example.jpamaster.flight.web.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RegisterAvailableAirlineRequestDto {
    private Long airlineSeq;
    private String availableFrom;
    private String availableTo;
}
