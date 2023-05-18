package com.example.jpamaster.accommodations.repository.room;

import com.example.jpamaster.accommodations.domain.entity.QRoom;
import com.example.jpamaster.accommodations.domain.entity.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class RoomCustomRepositoryImpl implements RoomCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public RoomCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Room> findByAccommodationSeq(Long seq) {
        return jpaQueryFactory.selectFrom(QRoom.room)
                .where(QRoom.room.accommodations.accommodationSeq.eq(seq))
                .fetch();
    }
}
