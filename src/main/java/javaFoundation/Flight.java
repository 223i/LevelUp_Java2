package javaFoundation;

import com.google.gson.Gson;

public class Flight {
    private String datum;              //2019.10.14 - формат
    private String departureTime;
    private String from;
    private String to;
    private String planeType;
    private int boardNumber;
    private String nameOfAirCompany;

    public Flight(String nameOfAirCompany, String datum,String departureTime, String from, String to,
                  String planeType,int boardNumber){
        this.nameOfAirCompany = nameOfAirCompany;
        this.datum = datum;
        this.departureTime = departureTime;
        this.from = from;
        this.to = to;
        this.planeType = planeType;
        this.boardNumber = boardNumber;
    }

    public String getDatum() {
        return datum;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPlaneType() {
        return planeType;
    }

    public int getBoardNumber() {
        return boardNumber;
    }

    public String getNameOfAirCompany() {
        return nameOfAirCompany;
    }

    public String flightToJson (Flight flight){
        Gson gson = new Gson();
        return gson.toJson(flight);
    }

    public Flight flightFromJson (String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Flight.class);
    }

    @Override
    public String toString() {
        return "{ AirCompany: " + this.nameOfAirCompany + " Datum: " + this.datum + " Departure time: " +
                this.departureTime + " From: " + this.from + " To: " + this.to + " PlaneType: " + this.planeType +
                " Board Number: " + this.boardNumber +" }";
    }
}
