package model;

import io.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payment.CalculatePayment;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StopPairTest {

    CSVReader csvReader = new CSVReader();
    Map<String , TripPart> tripParts;

    @Test
    public void shouldReturnZeroFromSameStop() {
        tripParts = csvReader.read("resources/same_stop.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(0.0, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldReturnCostStop1Stop2() {
        tripParts = csvReader.read("resources/stop1_stop2.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(3.25, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldReturnCostStop1Stop3() {
        tripParts = csvReader.read("resources/stop1_stop3.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(7.30, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldReturnCostStop2Stop3() {
        tripParts = csvReader.read("resources/stop2_stop3.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(5.5, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldChargeMaxStop1() {
        tripParts = csvReader.read("resources/stop1_invalid.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(7.30, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldChargeMaxStop2() {
        tripParts = csvReader.read("resources/stop2_invalid.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(5.5, completeTrips.get("1").getChargeAmount());
    }

    @Test
    public void shouldChargeMaxStop3() {
        tripParts = csvReader.read("resources/stop3_invalid.csv");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(7.3, completeTrips.get("1").getChargeAmount());
    }
}