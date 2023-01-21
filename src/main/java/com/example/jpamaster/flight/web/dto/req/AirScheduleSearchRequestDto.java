package com.example.jpamaster.flight.web.dto.req;

import com.example.jpamaster.flight.enums.FlightEnums.SeatType;
import com.example.jpamaster.flight.util.FlightUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AirScheduleSearchRequestDto {

    private Long departAirportSeq;
    private Long arriveAirportSeq;

    private Integer adultCount = 1;
    private Integer childCount;
    private Integer infantCount;

    private String departDate;
    private boolean isDirectFlight;
    private boolean isFreeBaggage;

    private SeatType seatType = SeatType.ECONOMY;

    @Hidden
    @JsonIgnore
    private LocalDateTime departAt;

    public boolean personCountValidation() {
        if (this.adultCount < 0
            || this.childCount < 0
            || this.infantCount < 0
            || (infantCount > 0 && adultCount <= 0)
        ) {
            return false;
        }

        return true;
    }

    @Hidden
    @JsonIgnore
    public int getTotalPersonCount() {
        return this.adultCount + this.childCount;
    }

    @Hidden
    @JsonIgnore
    public LocalDateTime getDepartAt() {
        return FlightUtils.toLocalDateTime(departDate, "0000");
    }

}
