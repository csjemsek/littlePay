package application;

import io.CSVReader;
import model.CompleteTrip;
import model.TripPart;
import payment.CalculatePayment;
import io.OutputTrips;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0)
        {
            System.out.println("please enter input file path");
            System.exit(0);
        }
        CSVReader csvReader = new CSVReader();
        Map<String , TripPart> tripParts = csvReader.read(args[0]);
        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);
        OutputTrips.printOutput(completeTrips);
    }
}
