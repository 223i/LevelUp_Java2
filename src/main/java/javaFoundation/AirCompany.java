package javaFoundation;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AirCompany {
    private String nameOfAirCompany;
    private ArrayList<Flight> allFlights = new ArrayList<>();

    public AirCompany(String nameOfCompany) {
        nameOfAirCompany = nameOfCompany;
    }

    public String getNameOfAirCompany() {
        return nameOfAirCompany;
    }


    public void appendNewFlight(Flight newFlight) {
        allFlights.add(newFlight);
    }

    public ArrayList<Flight> getAllFlights() {
        return new ArrayList<Flight>(allFlights);
    }

    public String airCompanyToJson (AirCompany airCompany){
        Gson gson = new Gson();
        return gson.toJson(airCompany);
    }

    public AirCompany airCompanyFromJson (String json){
        Gson gson = new Gson();
        return gson.fromJson(json, AirCompany.class);
    }
}
