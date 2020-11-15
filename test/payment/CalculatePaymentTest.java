package payment;

import io.CSVReader;
import model.CompleteTrip;
import model.TripPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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

    @Test
    public void shouldCalculateDuration(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusFive = now.plusSeconds(5);
        tripParts.get("0").setDateTimeUTC(now);
        tripParts.get("1").setDateTimeUTC(now);

        Map<String, CompleteTrip> completeTripsZero = CalculatePayment.getTripType(tripParts);
        assertEquals(0.0, completeTripsZero.get("1").getDuration());

        tripParts.get("1").setDateTimeUTC(plusFive);
        Map<String, CompleteTrip> completeTripFive = CalculatePayment.getTripType(tripParts);

        assertEquals(5.0, completeTripFive.get("1").getDuration());
    }

    @Test
    public void shouldCalculateMaxPriceInvalidTripStop1(){
        tripParts.get("0").setTapType("ON");
        tripParts.get("1").setTapType("ON");

        tripParts.get("0").setStopId("Stop1");

        Map<String, CompleteTrip> completeTripStop1 = CalculatePayment.getTripType(tripParts);
        assertEquals(7.3, completeTripStop1.get("1").getChargeAmount());

        tripParts.get("0").setStopId("Stop2");
        Map<String, CompleteTrip> completeTripStop2 = CalculatePayment.getTripType(tripParts);

        assertEquals(5.5, completeTripStop2.get("1").getChargeAmount());

        tripParts.get("0").setStopId("Stop3");
        Map<String, CompleteTrip> completeTripStop3 = CalculatePayment.getTripType(tripParts);
        assertEquals(7.3, completeTripStop3.get("1").getChargeAmount());
    }

}