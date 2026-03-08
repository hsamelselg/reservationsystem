package org.example.reservationsystem.repository;

import org.example.reservationsystem.dataobjects.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
