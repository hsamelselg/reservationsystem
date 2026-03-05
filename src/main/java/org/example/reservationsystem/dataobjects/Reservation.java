package org.example.reservationsystem.dataobjects;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "STARTTIME", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "ENDTIME")
    private LocalDateTime endTime;
    @Column(name = "size")
    private int size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_ID", referencedColumnName = "ID")
    private restaurant_table table;
    @Column(name = "INFO")
    private String client_info;

    public Reservation() {
    }

    public Reservation(String client_info, LocalDateTime endTime, int size, LocalDateTime startTime, restaurant_table table) {
        this.client_info = client_info;
        this.endTime = endTime;
        this.size = size;
        this.startTime = startTime;
        this.table = table;
    }
}
