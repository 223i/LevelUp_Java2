package javaFoundation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Booking {
    private Passenger passenger;
    private Flight flight;
    private AirCompany company;

    Booking(Passenger passenger, Flight flight, AirCompany company){
        this.passenger = passenger;
        this.flight = flight;
        this.company = company;
    }

    public  Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public AirCompany getCompany() {
        return company;
    }

    public String bookingToJson (Booking booking){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(booking);
    }

    public Booking bookingFromJson (String json){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, Booking.class);
    }
}
