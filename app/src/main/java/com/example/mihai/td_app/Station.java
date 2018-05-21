package com.example.mihai.td_app;

public class Station {
    private int station_id;
    private int available;
    private int occupied;
    private int reserved;

    public Station() {
    }

    public Station(int available, int occupied, int reserved) {
        this.available = available;
        this.occupied = occupied;
        this.reserved = reserved;
    }

    public Station(int station_id, int available, int occupied, int reserved) {
        this.station_id = station_id;
        this.available = available;
        this.occupied = occupied;
        this.reserved = reserved;
    }
    // setters
    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    //getters

    public int getStation_id() {
        return station_id;
    }

    public int getAvailable() {
        return available;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getReserved() {
        return reserved;
    }
}
