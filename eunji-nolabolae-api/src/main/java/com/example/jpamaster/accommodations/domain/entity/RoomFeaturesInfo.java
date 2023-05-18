package com.example.jpamaster.accommodations.domain.entity;

import com.example.jpamaster.common.domain.BaseEntity;
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
@Getter
@Table(name = "room_features_info")
public class RoomFeaturesInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_features_info_seq")
    private Long roomFeaturesInfoSeq;

    @ManyToOne
    @JoinColumn(name = "features_seq")
    private Features features;

    @Column(name = "sort_num")
    private int sort;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @Setter
    private Room room;
}
