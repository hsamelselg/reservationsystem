package org.example.reservationsystem.controllers;


import org.example.reservationsystem.BigGroupException;
import org.example.reservationsystem.InvalidDateException;
import org.example.reservationsystem.ReservationService;
import org.example.reservationsystem.dataobjects.Reservation;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.ReservationRepository;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestaurantController {
    private final TableRepository tableRepository;
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    public RestaurantController(TableRepository tableRepository, ReservationService reservationService,  ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }
    @GetMapping("/")
    public String showFloorPlan(Model model) {
        List<Restaurant_table> tables = tableRepository.findAll();

        model.addAttribute("tables", tables);
        model.addAttribute("isSearch", false);
        return "index";
    }

    @GetMapping("/search")
    public String showAvailableTables(
            Model model, @RequestParam("people") int people,
            @RequestParam("dateTime") String dateTime,
            @RequestParam("zone") String zone,
            @RequestParam(value="features", required = false) List<String> features) {
        try {
            Restaurant_table maxTable = tableRepository.findFirstByOrderBySizeDesc().orElseThrow(() -> new RuntimeException("Restoranis puuduvad lauad!"));
            if (people > maxTable.getSize()) {
                throw new BigGroupException("Vabandust, aga Teie seltskond on meie restorani jaoks liiga suur!<br>" +
                        "Soovitame broneerida mitu väiksemat lauda :) ");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(dateTime, formatter);

            LocalTime bookingTime = start.toLocalTime();
            LocalTime openingTime = LocalTime.of(10, 0);
            LocalTime closingTime = LocalTime.of(20, 0);

            if (start.isBefore(LocalDateTime.now())) {
                throw new InvalidDateException("Vabandust, aga minevikku ei saa lauda broneerida!");
            }

            if (bookingTime.isBefore(openingTime) || bookingTime.isAfter(closingTime)) {
                throw new InvalidDateException("Vabandust, laudu saab broneerida vahemikus 10:00 kuni 20:00.<br>" +
                        "Palun vali uus kellaaeg!");
            }

            LocalDateTime end = start.plusHours(2);

            List<Restaurant_table> available = reservationService.availableTables(people, start, end);

            Map<Restaurant_table, Integer> suitableTables = new HashMap<>();
            for (Restaurant_table t : available) {
                if (t != null) {
                    int fittingScore = calculateScore(t, people, zone, features);
                    suitableTables.put(t, fittingScore);
                }
            }

            Restaurant_table bestTable = null;
            for (Map.Entry<Restaurant_table, Integer> entry : suitableTables.entrySet()) {
                if (bestTable == null || entry.getValue() < suitableTables.get(bestTable)) {
                    bestTable = entry.getKey();
                }
            }

            model.addAttribute("tables", tableRepository.findAll());
            model.addAttribute("availableTables", available);
            model.addAttribute("bestTable", bestTable);
            model.addAttribute("isSearch", true);

            model.addAttribute("selectedDateTime", dateTime);
            return "index";

        } catch (InvalidDateException | BigGroupException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("tables", tableRepository.findAll());
            return "index";

        }

    }

        private int calculateScore (Restaurant_table t,int people, String zone, List < String > features){
            int score = 0;
            // table SIZE fitting points
            score += (t.getSize() - people);
            // tabel ZONE score
            if (zone.equals("Kõik")) {
                score -= 10;
            } else if (zone.equals("Saal") && t.getZone().equals("Saal")) {
                score -= 100;
            } else if (zone.equals("Terrass") && t.getZone().equals("Terrass")) {
                score -= 100;
            }
            // feature scores
            if (features != null) {
                for (String feature : features) {
                    if (t.getFeatures().contains(feature)) {
                        score -= 10;
                    }
                }
            }
            return score;
        }

        @PostMapping("/confirm-reservation")
        public String confirmReservation (
        @RequestParam("tableId") int tableId,
        @RequestParam("resvDate") String resvDate,
        @RequestParam("clientName") String clientName,
        @RequestParam("clientPhone") String clientPhone
    ){
            Restaurant_table table = tableRepository.findById(tableId).get();
            LocalDateTime start = LocalDateTime.parse(resvDate);
            LocalDateTime end = start.plusHours(2);

            Reservation reservation = new Reservation(table, start, end, table.getSize());
            reservation.setClientName(clientName);
            reservation.setClientPhone(clientPhone);

            reservationRepository.save(reservation);
            return "redirect:/?success=true";
        }

}
