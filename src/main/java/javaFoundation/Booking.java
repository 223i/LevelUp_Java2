package javaFoundation;

import com.google.gson.Gson;

public class Booking {
    private Passenger passenger;
    private Flight flight;
    private AirCompany company;

    Booking (Passenger passenger, Flight flight, AirCompany company){
        this.passenger = passenger;
        this.flight = flight;
        this.company = company;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public AirCompany getCompany() {
        return company;
    }

    public String bookingToJson (Booking booking){
        Gson gson = new Gson();
        return gson.toJson(booking);
    }

    public Booking passengerFromJson (String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Booking.class);
    }
}
