package application;

import io.CSVReader;
import model.TripPart;
import payment.CalculatePayment;

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
        CalculatePayment.getTripType(tripParts);
    }
}
