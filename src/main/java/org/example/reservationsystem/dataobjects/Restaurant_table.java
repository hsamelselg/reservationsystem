package org.example.reservationsystem.dataobjects;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name= "restaurant_table")
public class Restaurant_table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "SIZE", nullable = false)
    private int size;
    @OneToMany(mappedBy = "table_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
    @Column(name = "COORDINATEX", nullable = false)
    private double coordinateX;
    @Column(name = "COORDINATEY", nullable = false)
    private double coordinateY;
    @Column(name = "ZONE", nullable = false)
    private String zone;
    @Column(name = "ISWINDOWSEAT", nullable = false)
    private boolean isWindowSeat;
    @Column(name = "ISQUIETCORNER", nullable = false)
    private boolean isQuietCorner;
    @Column(name = "ACCESSIBLE", nullable = false)
    private boolean isAccessible;

    public Restaurant_table() {
    }
    public Restaurant_table(int size, double coordinateX, double coordinateY) {
        this.size = size;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Restaurant_table(double coordinateX, double coordinateY, boolean isAccessible, boolean isQuietCorner, boolean isWindowSeat, int size, String zone) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.reservations = reservations;
        this.isAccessible = isAccessible;
        this.isQuietCorner = isQuietCorner;
        this.isWindowSeat = isWindowSeat;
        this.size = size;
        this.zone = zone;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public boolean isQuietCorner() {
        return isQuietCorner;
    }

    public void setQuietCorner(boolean quietCorner) {
        isQuietCorner = quietCorner;
    }

    public boolean isWindowSeat() {
        return isWindowSeat;
    }

    public void setWindowSeat(boolean windowSeat) {
        isWindowSeat = windowSeat;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
