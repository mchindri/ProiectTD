package com.example.mihai.td_app;

import com.google.android.gms.maps.model.LatLng;

public class Station {
    private String name;
    private LatLng coords;
    private int station_id;
    private int available;
    private int occupied;
    private int reserved;

    public Station() {
    }


    public Station(int station_id, String name, int available, int occupied, int reserved, double lat, double lnt) {
        this.station_id = station_id;
        this.available = available;
        this.occupied = occupied;
        this.reserved = reserved;
        this.name = name;
        this.coords = new LatLng(lat, lnt);
    }

    //getters

    public String getName() {
        return name;
    }

    public LatLng getCoords() {
        return coords;
    }

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
}
