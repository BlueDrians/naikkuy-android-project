package com.example.naikkuy.model;

public class BookingModel {
    private final int id;
    private final String passengerName;
    private final String transport;
    private final String destination;
    private final String date;
    private final int ticketCount;
    private final int totalPrice;

    public BookingModel(int id, String passengerName, String transport, String destination, String date, int ticketCount, int totalPrice) {
        this.id = id;
        this.passengerName = passengerName;
        this.transport = transport;
        this.destination = destination;
        this.date = date;
        this.ticketCount = ticketCount;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getTransport() {
        return transport;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
