package com.example.jpamaster.flight.domain.repository;

import com.example.jpamaster.flight.domain.entity.Airplane;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    Optional<Airplane> findByAirplaneSeqAndAvailableIsTrue(Long airplaneSeq);

}
