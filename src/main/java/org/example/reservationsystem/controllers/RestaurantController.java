package org.example.reservationsystem.controllers;


import org.example.reservationsystem.ReservationService;
import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.ReservationRepository;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RestaurantController {
    private final TableRepository tableRepository;
    private final ReservationService reservationService;
    public RestaurantController(TableRepository tableRepository, ReservationService reservationService) {
        this.tableRepository = tableRepository;
        this.reservationService = reservationService;
    }
    @GetMapping("/")
    public String showFloorPlan(Model model) {
        List<Restaurant_table> tables = tableRepository.findAll();

        for (Restaurant_table t : tables) {
            if (t == null) System.out.println("LEIDSIN TÜHJA LAUA!");
            else System.out.println("Laud ID: " + t.getId() + " X: " + t.getCoordinateX());
        }

        model.addAttribute("tables", tables);

        return "index";
    }

    @GetMapping("/search")
    public String showAvailableTables(
            Model model, @RequestParam("people") int people,
            @RequestParam("dateTime") String dateTime) {
        LocalDateTime start = LocalDateTime.parse(dateTime);
        LocalDateTime end = start.plusHours(2);

        List<Restaurant_table> available = reservationService.availableTables(people, start, end);
        model.addAttribute("tables", tableRepository.findAll());
        model.addAttribute("availableTables", available);
        return "index";




    }
}
