package io;

import model.TripPart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CSVReader {


    //TODO : import all the tripLines and then instantiate the trip jobs separately
    public HashMap<String , TripPart> read(String inputFileName) {

        HashMap<String , TripPart> trips = new HashMap<>();
        String csvFile = inputFileName;
        String line = "";
        String cvsSplitBy = ", ";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            //Get separate input for the first line
            String headerLine = br.readLine();
            int counter = 0;

            while ((line = br.readLine()) != null) {
                TripPart tripPart = new TripPart();

                String[] tripLines = line.split(cvsSplitBy);

                //TODO : use a for loop
                //Add the values into an object
                tripPart.setiD(Integer.parseInt(tripLines[0]));
                tripPart.setDateTimeUTC(stringToDate(tripLines[1]));
                tripPart.setTapType(tripLines[2]);
                tripPart.setStopId(tripLines[3]);
                tripPart.setCompanyId(tripLines[4]);
                tripPart.setBusId(tripLines[5]);
                tripPart.setPan(tripLines[6]);

                String key = Integer.toString(counter);
                trips.put(key, tripPart);
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trips;
    }

    private LocalDateTime stringToDate(String tripLine) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tripLine, formatter);
        return dateTime;
    }

}
