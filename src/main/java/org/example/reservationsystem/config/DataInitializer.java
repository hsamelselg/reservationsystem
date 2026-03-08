package org.example.reservationsystem.config;

import org.example.reservationsystem.dataobjects.Reservation;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.ReservationRepository;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {
    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    public DataInitializer(ReservationRepository reservationRepository, TableRepository tableRepository) {
        this.reservationRepository=reservationRepository;
        this.tableRepository=tableRepository;
    }
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            int rows = 5;
            int yOffset = 50; // Reavahe

            for (int r = 0; r < rows; r++) {
                int currentX = 50;
                int seatsInRow = 0;

                while (seatsInRow < 10) {
                    // Valime suvaliselt laua suuruse: 2, 4 või 6
                    int size = (int) (Math.random() * 3 + 1) * 2;

                    Restaurant_table table = new Restaurant_table();
                    table.setCoordinateX(currentX);
                    table.setCoordinateY(yOffset + (r * 100)); // Ridade vahe 100px
                    table.setSize(size);
                    table.setZone("Saal");
                    table.setAccessible(true);
                    table.setQuietCorner(false);
                    table.setWindowSeat(r == 0); // Esimene rida akna all

                    tableRepository.save(table);

                    // Arvutame järgmise laua asukoha reas
                    // 2-kohaline võtab 60px, 4-kohaline 120px jne
                    currentX += (size / 2) * 60 + 20;
                    seatsInRow += size;
                }
            }
            List<Restaurant_table> restaurantTables = tableRepository.findAll();

            for (int i = 0; i < 3; i++) {
                int rand_table_number = (int) (Math.random() * restaurantTables.size());
                Restaurant_table resv_table = restaurantTables.get(rand_table_number);
                LocalDateTime startTime = LocalDateTime.now().plusHours((int) (Math.random() * 24));
                LocalDateTime endTime = startTime.plusHours(2);
                int size = resv_table.getSize();
                Reservation reservation = new Reservation(resv_table, startTime, endTime, size);
                reservationRepository.save(reservation);
            }

        }
    }
}
