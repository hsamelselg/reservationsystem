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
    @ElementCollection
    private List<String> features;

    public Restaurant_table() {
    }

    public Restaurant_table(int size, double coordinateX, double coordinateY) {
        this.size = size;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Restaurant_table(double coordinateX, double coordinateY, int size, String zone, List<String> features) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.size = size;
        this.zone = zone;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Restaurant_table{" +
                " id=" + id +
                ", size=" + size +
                ", zone='" + zone +
                ", features=" + features +
                ", reservations=" + reservations +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY + '\'' +
                '}';
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

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
