package org.example.reservationsystem.dataobjects;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name= "restaurant_tables")
public class restaurant_table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "SIZE", nullable = false)
    private int size;
    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
    @Column(name = "COORDINATEX", nullable = false)
    private double coordinateX;
    @Column(name = "COORDINATEY", nullable = false)
    private double coordinateY;
    @Column(name = "ZONE", nullable = false)
    private String zone;
    @Column(name = "WINDOW", nullable = false)
    private boolean isWindowSeat;
    @Column(name = "CORNER", nullable = false)
    private boolean isQuietCorner;
    @Column(name = "ACCESSIBLE", nullable = false)
    private boolean isAccessible;

    public restaurant_table() {
    }

    public restaurant_table(double coordinateX, double coordinateY, int id, boolean isAccessible, boolean isQuietCorner, boolean isWindowSeat, int size, String zone) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.id = id;
        this.reservations = reservations;
        this.isAccessible = isAccessible;
        this.isQuietCorner = isQuietCorner;
        this.isWindowSeat = isWindowSeat;
        this.size = size;
        this.zone = zone;
    }
}
