package io;

import model.CompleteTrip;

import java.io.FileWriter;
import java.util.Map;
import java.io.File;
import java.io.IOException;


public class OutputTrips {

    public static void printOutput(Map<String, CompleteTrip> completeTrips){

        File file = new File("trips.csv");
        try {
            FileWriter fileWriter = new FileWriter("trips.csv");
            fileWriter.write("Started,\tFinished,\tDurationSecs,\tFromStopId,\tToStopId,\tChargeAmount,\tCompanyId,\tBusID,\tPAN,\tStatus\n");
            completeTrips.forEach((k, v) -> {
                try {
                    fileWriter.write(
                            v.getTripPart1().getDateTimeUTC().toString().replace("T", " ") + ", " +
                                    v.getTripPart2().getDateTimeUTC().toString().replace("T", " ") + ", " +
                                    v.getDuration() + ", " +
                                    v.getTripPart1().getStopId() + ", " +
                                    v.getTripPart2().getStopId() + ", " +
                                    "$" + v.getChargeAmount() + ", " +
                                    v.getTripPart1().getCompanyId() + ", " +
                                    v.getTripPart1().getBusId() + ", " +
                                    v.getTripPart1().getPan() + ", " +
                                    v.getStatus() + "\n"
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        completeTrips.forEach((k, v) -> System.out.println((k + ":" + v.getChargeAmount())));
    }

}
