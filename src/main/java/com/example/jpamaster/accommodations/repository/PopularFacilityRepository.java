package com.example.jpamaster.accommodations.repository;

import com.example.jpamaster.accommodations.domain.entity.PopularFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularFacilityRepository extends JpaRepository<PopularFacility, Long> {
}
