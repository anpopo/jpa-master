package com.example.jpamaster.flight.domain.repository.airschedule;

import com.example.jpamaster.flight.web.dto.req.AirScheduleSearchRequestDto;
import com.example.jpamaster.flight.web.dto.res.AirScheduleSearchResponseDto;
import org.springframework.data.domain.Slice;

public interface AirScheduleCustomRepository {

    Slice<AirScheduleSearchResponseDto> findAirScheduleBySearch(AirScheduleSearchRequestDto dto);
}
