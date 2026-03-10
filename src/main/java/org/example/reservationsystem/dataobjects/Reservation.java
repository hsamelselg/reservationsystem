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
    @Column(name = "CLIENTNAME")
    private String clientName;
    @Column(name = "CLIENTPHONE")
    private String clientPhone;


    public Reservation() {
    }

    public Reservation(String clientName,String clientPhone, LocalDateTime endTime, int size, LocalDateTime startTime, Restaurant_table table_id) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "client_info='" + clientName + ", " + clientPhone + '\'' +
                ", id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", size=" + size +
                ", table_id=" + table_id.getId() +
                '}';
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Restaurant_table getTable_id() {
        return table_id;
    }

    public void setTable_id(Restaurant_table table_id) {
        this.table_id = table_id;
    }
}
