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
    @Column(name = "SIZE")
    private int size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_ID", referencedColumnName = "ID")
    private Restaurant_table table_id;
    @Column(name = "CLIENT_INFO")
    private String client_info;

    public Reservation() {
    }

    public Reservation(String client_info, LocalDateTime endTime, int size, LocalDateTime startTime, Restaurant_table table_id) {
        this.client_info = client_info;
        this.endTime = endTime;
        this.size = size;
        this.startTime = startTime;
        this.table_id = table_id;
    }

    public Reservation(Restaurant_table table_id, LocalDateTime startTime, LocalDateTime endTime, int size) {
        this.endTime = endTime;
        this.size = size;
        this.startTime = startTime;
        this.table_id = table_id;
    }
}
