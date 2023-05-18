package com.example.jpamaster.flight.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Embeddable
public class SeatRowAndColumn {

    @Comment("좌석 행")
    @Column(name = "seat_row")
    private Integer seatRow;

    @Comment("좌석 열")
    @Column(name = "seat_column", length = 1)
    private Character seatColumn;
}
