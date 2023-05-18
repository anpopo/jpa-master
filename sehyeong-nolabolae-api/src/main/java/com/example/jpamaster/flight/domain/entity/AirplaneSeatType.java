package com.example.jpamaster.flight.domain.entity;

import com.example.jpamaster.flight.enums.FlightEnums;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Table(name = "airplane_seat_type")
@AttributeOverride(
    name = "seq",
    column = @Column(name = "airplane_seat_type_seq")
)
@Entity
public class AirplaneSeatType extends SeatType{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_seq")
    private Airplane airplane;

    @Builder
    public AirplaneSeatType (FlightEnums.SeatType seatType, Integer availableSeatCount) {
        super(seatType, availableSeatCount);
    }

    public void registerAirplane(Airplane airplane) {
        this.airplane = airplane;
        airplane.getAirplaneSeatTypes().add(this);
    }
}
