package javaFoundation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Random;

public class AviaBooking {
    public static void main(String[] args) throws InterruptedException {
        String[] firstNamesDictionary = new String[] { "Jack", "John", "Samuel", "Tomas", "Daniel", "Harry","Michael"};
        String[] lastName = new String[] { "Luis", "Carrol", "Dwait", "Qwerty", "Smith", "Poul" };
        String[] gender = new String[] { "w", "m" };
        String[] citizenship = new String[] { "Canada", "France", "Japain", "China", "Russia", "Germany", "USA", "Italy"};
        String[] city = new String[] { "St.Petersburg", "Toronto", "Paris", "Rome", "Frankfurt", "Barcelona" };
        String[] nameOfAirCompany = new String[] { "AirBaltic", "Lufthansa", "Aeroflot", "KLM", "AlItalia", "FinAir", "UtAir", "BelAvia" };
        String[] datumOfFlight = new String[] { "2019.10.14", "2019.11.04", "2019.09.05", "2019.10.16", "2019.12.14", "2019.08.03", "2019.10.28"};
        String[] time = new String[] { "17.00", "12.00", "00.05", "03.45", "16.39", "19.25", "21.40"};
        String[] planeType = new String[] { "A330", "Boeing747", "A320", "A310", "ИЛ-96М"};

        final ArrayList<AirCompany> allAirCompanies = createDataAirCompany(nameOfAirCompany);   // создаем список компаний
        final ArrayList<AirCompany> allAirCompaniesWithFlights = createAllFlightsInAllCompanies(allAirCompanies,datumOfFlight, time,
                city,city,planeType);

        // Code to check the TestData
//        for (AirCompany airComp : allAirCompaniesWithFlights){
//            System.out.println(airComp.getNameOfAirCompany());
//            for (Flight everyFlight : airComp.getAllFlights()){
//                System.out.println(everyFlight.getDatum() + " " + everyFlight.getDepartureTime() +" "+ everyFlight.getFrom()+ " "
//                        + everyFlight.getTo()+ " "+ everyFlight.getPlaneType()+ " " + everyFlight.getBoardNumber());
//            }
//        }

        final Passenger newPassenger = createOnePassengerWithRandomData(firstNamesDictionary,lastName,gender,citizenship);


//        //Serialize passenger to json
//        String resultOfSerial = newPassenger.passengerToJson(newPassenger);
//        System.out.println(resultOfSerial);
//
//        //Deserialize json to passenger
//        Passenger resultOfDeSerial = newPassenger.passengerFromJson(resultOfSerial);
//        System.out.println(resultOfDeSerial);

//        createBooking(newPassenger, allAirCompaniesWithFlights);

        ArrayList<String> resultOfAllSerialisation = new ArrayList<>();

        Runnable target = new Runnable() {
            @Override
            public void run() {
                Booking newBooking = null;
                try {
                    newBooking = createBooking(newPassenger, allAirCompaniesWithFlights);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (resultOfAllSerialisation){
                    String resultOfSerial = newBooking.bookingToJson(newBooking);
                    resultOfAllSerialisation.add(resultOfSerial);
                }

            }
        };

        ArrayList<Thread> allThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Thread treadForBooking = new Thread(target,  i + "Booking");
            treadForBooking.start();
            synchronized (allThreads){
                allThreads.add(treadForBooking);
            }
        }
        for (Thread tread:allThreads){
            tread.join();
        }


            //Deserialize and print
            for (int i = 0; i < resultOfAllSerialisation.size(); i++) {
                synchronized (resultOfAllSerialisation) {
                    Gson newGson = new GsonBuilder().setPrettyPrinting().create();
                    String deserialization = resultOfAllSerialisation.get(i);
                    Booking getBooking = newGson.fromJson(deserialization, Booking.class);
                    System.out.println(getBooking);
            }
        }
    }

    public static Booking createBooking(Passenger passenger, ArrayList<AirCompany> allAirCompanies) throws Exception{
        Random random = new Random();
        String from = "Toronto";  //желаемый пункт отправления
        String to = "Rome"; // желаемый пункт прибытия
        Booking newBooking = null;

        for (AirCompany airCompany : allAirCompanies){
            for(Flight everyFlight : airCompany.getAllFlights()){
                if (everyFlight.getFrom().equals(from) && everyFlight.getTo().equals(to)){
                    newBooking = new Booking(passenger,everyFlight, airCompany);
                    passenger.appendCreatedBooking(newBooking);
//                    System.out.println(passenger.getFirstName() + " " + passenger.getSecondName() + " " + everyFlight.getDatum() + " " + everyFlight.getDepartureTime() +" "+ everyFlight.getFrom()+ " "
//                            + everyFlight.getTo()+ " "+ everyFlight.getPlaneType()+ " " + everyFlight.getBoardNumber() + " " + airCompany.getNameOfAirCompany());
                    return newBooking;
                } else{
                    newBooking = new Booking(passenger, airCompany.getAllFlights().get(random.nextInt(airCompany.getAllFlights().size())), airCompany); ;
                    passenger.appendCreatedBooking(newBooking);
                    return newBooking;
                }
            }
        }
        return newBooking;
    }

    public static Passenger createOnePassengerWithRandomData(String[] firstNames,String[] lastName, String[] gender,
                                                             String[] citizenship){
        Random random = new Random();
        String firstNameOfPassenger = firstNames[random.nextInt(firstNames.length)];
        String lastNameOfPassenger = lastName[random.nextInt(lastName.length)];
        String genderOfPassenger = gender[random.nextInt(gender.length)];
        int ageOfPassenger = random.nextInt(100);
        String citizenshipOfPassenger = citizenship[random.nextInt(citizenship.length)];
        Passenger newPassenger = new Passenger(firstNameOfPassenger,lastNameOfPassenger,genderOfPassenger, ageOfPassenger,
                citizenshipOfPassenger);

        return newPassenger;

    }


    public static ArrayList<AirCompany> createAllFlightsInAllCompanies(ArrayList<AirCompany> allAirCompanies, String[] datumOfFlight,
                                                                       String[] time, String[] from, String[] to,
                                                                       String[] planeType){

        for (int i = 0; i< allAirCompanies.size(); i++){
            Random random = new Random();
            AirCompany airCompany = allAirCompanies.get(i);
            String airCompanyName = allAirCompanies.get(i).getNameOfAirCompany();

            for (int eachflight = 0; eachflight< 10; eachflight++) {
                String date = datumOfFlight[random.nextInt(datumOfFlight.length)];
                String timeOfFlight = time[random.nextInt(time.length)];
                String from_ = from[random.nextInt(from.length)];
                String destination = to[random.nextInt(to.length)];
                String typeOfPlane = planeType[random.nextInt(planeType.length)];
                int boardNumber = random.nextInt(15);

                Flight newFlight = new Flight(airCompanyName, date, timeOfFlight, from_, destination,typeOfPlane, boardNumber);
                airCompany.appendNewFlight(newFlight);
            }
        }
        return allAirCompanies;
    }

    public static ArrayList<AirCompany> createDataAirCompany(String[] nameOfAirCompany){
        Random random = new Random();
        ArrayList<AirCompany> allAirCompanies = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            String name = nameOfAirCompany[random.nextInt(nameOfAirCompany.length)];
            AirCompany newAirCompany = new AirCompany(name);
            allAirCompanies.add(newAirCompany);
        }
        return allAirCompanies;
    }

}

