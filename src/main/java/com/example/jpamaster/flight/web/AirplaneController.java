package com.example.jpamaster.flight.web;

import com.example.jpamaster.common.ApiResponse;
import com.example.jpamaster.flight.service.AirplaneService;
import com.example.jpamaster.flight.web.dto.req.AirplaneRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
