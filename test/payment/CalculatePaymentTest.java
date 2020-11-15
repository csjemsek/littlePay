package payment;

import io.CSVReader;
import model.CompleteTrip;
import model.TripPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatePaymentTest {
    CSVReader csvReader = new CSVReader();
    Map<String , TripPart> tripParts = csvReader.read("input.csv");
    @BeforeEach
    public void setup() {

    }

    @Test
    void getTripType() {
    }

    @Test
    public void shouldHaveCompleteStatusWhenValid(){

        tripParts.get("0").setTapType("ON");
        tripParts.get("1").setTapType("OFF");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(completeTrips.get("1").getStatus(), CompleteTrip.Status.COMPLETE);

    }

    @Test
    public void shouldHaveIncompleteStatusWhenInvalid(){
        tripParts.get("0").setTapType("ON");
        tripParts.get("1").setTapType("ON");

        Map<String, CompleteTrip> completeTrips = CalculatePayment.getTripType(tripParts);

        assertEquals(completeTrips.get("1").getStatus(), CompleteTrip.Status.INCOMPLETE);

    }
}