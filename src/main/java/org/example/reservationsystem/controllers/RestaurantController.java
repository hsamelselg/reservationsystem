package org.example.reservationsystem.controllers;


import org.example.reservationsystem.dataobjects.Restaurant_table;
import org.example.reservationsystem.repository.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestaurantController {
    private final TableRepository tableRepository;
    public RestaurantController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
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
}
