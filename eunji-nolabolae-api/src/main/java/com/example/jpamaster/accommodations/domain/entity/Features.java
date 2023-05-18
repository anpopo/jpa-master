package com.example.jpamaster.accommodations.domain.entity;

import com.example.jpamaster.common.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "features")
public class Features extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "features_seq")
    private Long featuresSeq;

    @Column(name = "features_name")
    private String featuresName;

    @Column(name = "features_icon_url")
    private String featuresIconUrl;

}
