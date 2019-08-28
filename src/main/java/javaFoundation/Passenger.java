package javaFoundation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Passenger {
    private String firstName;
    private String secondName;
    private String gender;
    private int age;
    private String citizenship;
    ArrayList<Booking> createdBooking = new ArrayList<>();

    public Passenger(String firstName, String secondName, String gender, int age, String citizenship){
        if (firstName== null || secondName == null || gender == null || age <= 0 || citizenship == null ) {
            throw new IllegalArgumentException("Name schould be specified and age must be > 0");
        }
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.age = age;
        this.citizenship = citizenship;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void appendCreatedBooking(Booking newBooking) {
        createdBooking.add(newBooking);
    }

    public ArrayList<Booking>  getCreatedBooking(){
        return createdBooking;
    }

    public String passengerToJson (Passenger passenger){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(passenger);
    }

    public Passenger passengerFromJson (String json){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, Passenger.class);
    }
}
