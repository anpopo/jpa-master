package com.example.jpamaster.accommodations.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accommodation_facility_info")
@Getter
public class AccommoFacilityInfo {

    @Column(name = "accommo_facility_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long seq;

    @JoinColumn(name = "accommodations_seq")
    @ManyToOne
    @Setter
    private Accommodations accommodation;

    @JoinColumn(name = "popular_facility_seq")
    @ManyToOne
    private PopularFacility popularFacility;

    private Integer sort;
}
