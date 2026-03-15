package org.example.reservationsystem.config;

import org.example.reservationsystem.ReservationService;
import org.example.reservationsystem.dataobjects.Reservation;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.ReservationRepository;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {
    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    private final ReservationService reservationService;

    public DataInitializer(ReservationRepository reservationRepository, TableRepository tableRepository,  ReservationService reservationService) {
        this.reservationRepository=reservationRepository;
        this.tableRepository=tableRepository;
        this.reservationService=reservationService;
    }
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            tableRepository.save(new Restaurant_table(50, 50,  2, "Saal", List.of("Vaikne nurk", "Seina lähedal"))); // 2p
            tableRepository.save(new Restaurant_table(175, 50, 6, "Saal", List.of("Seina lähedal"))); // 6p
            tableRepository.save(new Restaurant_table(400, 50, 2, "Saal", List.of("Akna all", "Vaikne nurk", "Seina lähedal"))); // 2p

            // 2. rida
            tableRepository.save(new Restaurant_table(50, 150, 4, "Saal", List.of("Seina lähedal"))); // 4p
            tableRepository.save(new Restaurant_table(250, 150, 2, "Saal", List.of())); // 2p
            tableRepository.save(new Restaurant_table(400, 150, 2, "Saal", List.of("Akna all"))); // 2p

            // 3. rida
            tableRepository.save(new Restaurant_table(100, 250, 4, "Saal", List.of())); // 4p
            tableRepository.save(new Restaurant_table(300, 250, 4, "Saal", List.of("Akna all"))); // 4p

            // 4. rida
            tableRepository.save(new Restaurant_table(50, 350, 6, "Saal", List.of("Seina lähedal", "Vaikne nurk"))); // 6p
            tableRepository.save(new Restaurant_table(375, 350, 4, "Saal", List.of("Akna all", "Mängunurga lähedal"))); // 2p

            // --- TERASS (Parem pool) ---
            tableRepository.save(new Restaurant_table(575, 50, 6, "Terrass", List.of("Seina lähedal"))); // 6p (ülal)
            tableRepository.save(new Restaurant_table(550, 150, 2, "Terrass", List.of("Seina lähedal"))); // 2p
            tableRepository.save(new Restaurant_table(675, 150, 2, "Terrass", List.of())); // 2p
            tableRepository.save(new Restaurant_table(550, 250, 4, "Terrass", List.of("Seina lähedal"))); // 4p
            tableRepository.save(new Restaurant_table(650, 350, 4, "Terrass", List.of("Vaikne nurk"))); // 4p


            List<Restaurant_table> restaurantTables = tableRepository.findAll();

            for (int i = 0; i < restaurantTables.size()/2; i++) {
                int rand_table_number = (int) (Math.random() * restaurantTables.size());
                Restaurant_table resv_table = restaurantTables.get(rand_table_number);
                int hour = (int) (Math.random() * 11) + 10;
                LocalDateTime startTime = LocalDateTime.now()
                        .withHour(hour)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);
                if (startTime.isBefore(LocalDateTime.now())) {
                    startTime = startTime.plusDays(1);
                }
                LocalDateTime endTime = startTime.plusHours(2);
                int size = resv_table.getSize();
                Reservation reservation = new Reservation(resv_table, startTime, endTime, size);
                reservationRepository.save(reservation);
            }

        }
    }
}
