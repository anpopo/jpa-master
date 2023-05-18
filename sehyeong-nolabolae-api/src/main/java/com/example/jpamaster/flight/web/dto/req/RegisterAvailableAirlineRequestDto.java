package com.example.jpamaster.flight.web.dto.req;

import lombok.Getter;

@Getter
public class RegisterAvailableAirlineRequestDto {
    private Long airlineSeq;
    private String availableFrom;
    private String availableTo;
}
