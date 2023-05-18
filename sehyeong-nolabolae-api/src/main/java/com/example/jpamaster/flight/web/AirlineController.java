package com.example.jpamaster.flight.web;

import com.example.jpamaster.flight.service.AirlineService;
import com.example.jpamaster.flight.web.dto.req.AirlineUpdateRequestDto;
import com.example.jpamaster.flight.web.dto.req.KeywordSearchConditionDto;
import com.example.jpamaster.flight.web.dto.res.AirlineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/admin/airline")
@RequiredArgsConstructor
@RestController
public class AirlineController {

    private final AirlineService airlineService;

    @DeleteMapping("/{airlineSeq}")
    public void deleteAirline(
            @PathVariable("airlineSeq") Long airlineSeq
    ) {
        airlineService.deleteAirline(airlineSeq);
    }

    @PutMapping("/{airlineSeq}")
    public Long updateAirlineInfo(
            @PathVariable("airlineSeq") Long airlineSeq,
            @RequestBody AirlineUpdateRequestDto dto,
            @RequestPart(name = "airlineImage", required = false) MultipartFile airlineImage
    ) {
        return airlineService.updateAirlineInfo(airlineSeq, dto, airlineImage);
    }

    @GetMapping
    public Page<AirlineDto> getAirlineList(
            KeywordSearchConditionDto airlineSearchCondition,
            Pageable pageable
    ) {
        return airlineService.getAirlineListByCondition(airlineSearchCondition, pageable);
    }
}
