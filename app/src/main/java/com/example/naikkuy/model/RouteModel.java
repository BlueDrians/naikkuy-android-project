package com.example.naikkuy.model;

public class RouteModel {
    private final String vehicle;
    private final String route;
    private final String duration;
    private final int fare;

    public RouteModel(String vehicle, String route, String duration, int fare) {
        this.vehicle = vehicle;
        this.route = route;
        this.duration = duration;
        this.fare = fare;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getRoute() {
        return route;
    }

    public String getDuration() {
        return duration;
    }

    public int getFare() {
        return fare;
    }
}
