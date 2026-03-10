package org.example.reservationsystem.controllers;


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
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        for (Restaurant_table t : tables) {
            if (t == null) System.out.println("LEIDSIN TÜHJA LAUA!");
            else System.out.println("Laud ID: " + t.getId() + " X: " + t.getCoordinateX());
        }

        model.addAttribute("tables", tables);
        model.addAttribute("isSearch", false);
        return "index";
    }

    @GetMapping("/search")
    public String showAvailableTables(
            Model model, @RequestParam("people") int people,
            @RequestParam("dateTime") String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(dateTime, formatter);
        LocalDateTime end = start.plusHours(2);

        List<Restaurant_table> available = reservationService.availableTables(people, start, end);

        model.addAttribute("tables", tableRepository.findAll());
        model.addAttribute("availableTables", available);
        model.addAttribute("isSearch", true);

        model.addAttribute("selectedDateTime", dateTime);
        return "index";
    }

    @PostMapping("/confirm-reservation")
    public String confirmReservation(
            @RequestParam("tableId") int tableId,
            @RequestParam("resvDate") String resvDate,
            @RequestParam("clientName") String clientName,
            @RequestParam("clientPhone") String clientPhone
    ) {
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
