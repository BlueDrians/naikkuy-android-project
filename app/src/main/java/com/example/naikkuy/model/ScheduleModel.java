package com.example.naikkuy.model;

public class ScheduleModel {
    private final String time;
    private final String vehicle;
    private final String destination;
    private final String status;

    public ScheduleModel(String time, String vehicle, String destination, String status) {
        this.time = time;
        this.vehicle = vehicle;
        this.destination = destination;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }
}
