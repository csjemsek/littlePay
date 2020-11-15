package application;

import com.sun.media.jfxmedia.logging.Logger;
import io.CSVReader;
import model.TripPart;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("arguments " + args[0]);
        CSVReader csvReader = new CSVReader();
        Map<String , TripPart> tripParts = csvReader.read(args[0]);
    }
}
