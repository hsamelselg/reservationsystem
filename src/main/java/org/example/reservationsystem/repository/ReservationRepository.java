package org.example.reservationsystem.repository;

import org.example.reservationsystem.dataobjects.Reservation;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.table_id = :table "+
            "AND NOT (r.endTime <= :start OR r.startTime >= :end)")
    List<Reservation> findOverlappingReservations(
            @Param("table") Restaurant_table table,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

}
