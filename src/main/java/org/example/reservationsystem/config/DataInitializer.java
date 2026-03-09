package org.example.reservationsystem.config;

import org.example.reservationsystem.ReservationService;
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
    private final ReservationService reservationService;

    public DataInitializer(ReservationRepository reservationRepository, TableRepository tableRepository,  ReservationService reservationService) {
        this.reservationRepository=reservationRepository;
        this.tableRepository=tableRepository;
        this.reservationService=reservationService;
    }
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            tableRepository.save(new Restaurant_table(50, 50, true, true, false, 2, "Saal")); // 2p
            tableRepository.save(new Restaurant_table(175, 50, false, false, false, 6, "Saal")); // 6p
            tableRepository.save(new Restaurant_table(400, 50, true, true, true, 2, "Saal")); // 2p

            // 2. rida
            tableRepository.save(new Restaurant_table(50, 150, false, false, false, 4, "Saal")); // 4p
            tableRepository.save(new Restaurant_table(250, 150, true, false, false, 2, "Saal")); // 2p
            tableRepository.save(new Restaurant_table(400, 150, true, false, true, 2, "Saal")); // 2p

            // 3. rida
            tableRepository.save(new Restaurant_table(100, 250, false, false, false, 4, "Saal")); // 4p
            tableRepository.save(new Restaurant_table(300, 250, true, false, false, 4, "Saal")); // 4p

            // 4. rida
            tableRepository.save(new Restaurant_table(50, 350, false, true, false, 6, "Saal")); // 6p
            tableRepository.save(new Restaurant_table(375, 350, true, false, true, 4, "Saal")); // 2p

            // --- TERASS (Parem pool) ---
            tableRepository.save(new Restaurant_table(575, 50, true, false, true, 6, "Terass")); // 6p (ülal)
            tableRepository.save(new Restaurant_table(550, 150, true, false, true, 2, "Terass")); // 2p
            tableRepository.save(new Restaurant_table(675, 150, true, false, true, 2, "Terass")); // 2p
            tableRepository.save(new Restaurant_table(550, 250, true, false, true, 4, "Terass")); // 4p
            tableRepository.save(new Restaurant_table(650, 350, true, false, true, 4, "Terass")); // 4p


            List<Restaurant_table> restaurantTables = tableRepository.findAll();

            for (int i = 0; i < restaurantTables.size()/2; i++) {
                int rand_table_number = (int) (Math.random() * restaurantTables.size());
                Restaurant_table resv_table = restaurantTables.get(rand_table_number);
                LocalDateTime startTime = LocalDateTime.now().plusHours((int) (Math.random() * 24));
                LocalDateTime endTime = startTime.plusHours(2);
                int size = resv_table.getSize();
                Reservation reservation = new Reservation(resv_table, startTime, endTime, size);
                reservationRepository.save(reservation);
            }
            System.out.println(reservationRepository.findAll());

        }
    }
}
