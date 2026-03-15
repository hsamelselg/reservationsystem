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
        List<Restaurant_table> fittingTables = tableRepository.findBySizeGreaterThanEqual(people);

        List<Restaurant_table> exactMatchAvailable = new ArrayList<>();
        List<Restaurant_table> largerAvailable = new ArrayList<>();

        for (Restaurant_table fittingTable : fittingTables) {
            List<Reservation> reservations = reservationRepository.findOverlappingReservations(fittingTable, start, end);

            if (reservations.isEmpty()) {
                if (fittingTable.getSize() == people || fittingTable.getSize() == people+1) {
                    exactMatchAvailable.add(fittingTable);
                } else {
                    largerAvailable.add(fittingTable);
                }
            }
        }

        if (!exactMatchAvailable.isEmpty()) {
            return exactMatchAvailable;
        } else {
            return largerAvailable;
        }
    }
}
