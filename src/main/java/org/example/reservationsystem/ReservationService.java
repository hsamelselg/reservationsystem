package org.example.reservationsystem;

import org.example.reservationsystem.dataobjects.Reservation;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.ReservationRepository;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, TableRepository tableRepository) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
    }

    public List<Restaurant_table> availableTables(int people, LocalDateTime start, LocalDateTime end) {
        List<Restaurant_table> FittingTables = tableRepository.findBySizeGreaterThanEqual(people);
        List<Restaurant_table> availableTables = new ArrayList<>();
        for (Restaurant_table FittingTable : FittingTables) {
            List<Reservation> Reservations = reservationRepository.findOverlappingReservations(FittingTable, start, end);
            if (Reservations.isEmpty()) {
                availableTables.add(FittingTable);
            }
        }
        return availableTables;
    }
}
