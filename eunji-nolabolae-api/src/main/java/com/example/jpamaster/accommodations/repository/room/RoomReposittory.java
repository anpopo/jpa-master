package com.example.jpamaster.accommodations.repository.room;

import com.example.jpamaster.accommodations.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomReposittory extends JpaRepository<Room, Long>, RoomCustomRepository{
}
