package com.example.jpamaster.flight.web;

import com.example.jpamaster.flight.service.AirplaneService;
import com.example.jpamaster.flight.web.dto.req.AirplaneRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/{airlineSeq}/airplane")
@RequiredArgsConstructor
@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    @PostMapping
    public void registerAirplane (
            @PathVariable("airlineSeq") Long airlineSeq,
            @RequestBody AirplaneRegisterRequestDto dto
    ) {
        airplaneService.registerAirplane(airlineSeq, dto);
    }
}
