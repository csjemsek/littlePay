package payment;

import model.CompleteTrip;
import model.StopPair;
import model.TripPart;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

//TODO : need to be able to compare stops
public class CalculatePayment {

    //TODO : refactor into smaller methods
    public static Map<String, CompleteTrip> getTripType(Map<String , TripPart> trips) {
        LOGGER.info("commencing cost calculation");

        Map<String, CompleteTrip> completeTrips = new HashMap<>();
        Iterator iterator = trips.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry tripEntry = (Map.Entry)iterator.next();
            TripPart tripPart = (TripPart) tripEntry.getValue();
            TripPart nextTripPart = (TripPart) ((Map.Entry) iterator.next()).getValue();

            if (tripPart.getTapType().equals("ON")){
                CompleteTrip completeTrip = createCompleteTrip(tripPart, nextTripPart);
                completeTrips.put(Integer.toString(tripPart.getiD()), completeTrip);
            }
            //iterator.remove();
        }
        return completeTrips;
    }


    private static CompleteTrip createCompleteTrip(TripPart tripPart1, TripPart tripPart2) {
        double fare = 0.0;
        CompleteTrip completeTrip = new CompleteTrip();
        LocalDateTime departureTime = tripPart1.getDateTimeUTC();
        LocalDateTime arrivalTime = tripPart2.getDateTimeUTC();

        completeTrip.setTripPart1(tripPart1);
        completeTrip.setTripPart2(tripPart2);
        completeTrip.setDuration(getDuration(departureTime, arrivalTime));

        if (tripPart2.getTapType().equals("ON")){
            completeTrip.setChargeAmount(calculateIncompleteTripFare(tripPart1));
            completeTrip.setStatus(CompleteTrip.Status.INCOMPLETE);
            return completeTrip;
        }

        completeTrip.setChargeAmount(calculateCompleteTripFare(tripPart1, tripPart2));
        completeTrip.setStatus(CompleteTrip.Status.COMPLETE);

        return completeTrip;
    }

    private static double getDuration(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        double duration = 0.0;

        duration = departureTime.until(arrivalTime, ChronoUnit.SECONDS);
        LOGGER.info("Duration in seconds: " + duration);

        return duration;
    }

    private static double calculateCompleteTripFare(TripPart tripPart, TripPart nextTripPart) {
        LOGGER.info("complete tripPart called");
        String firstStop = tripPart.getStopId();
        String secondStop = nextTripPart.getStopId();

        LOGGER.info("stops: \n" + firstStop + "\n" + secondStop);
        StopPair stopPair = new StopPair(firstStop, secondStop);
        double fare = stopPair.getPairing(firstStop, secondStop);
        LOGGER.info("complete tripPart fare: " + fare);
        return  fare;
    }

    private static double calculateIncompleteTripFare(TripPart tripPart) {
        LOGGER.info("max costs tripPart called");
        double maxFare = 0.0;
        String stop = tripPart.getStopId();

        //TODO : make this an enum
        switch(stop)
        {
            case "Stop1":
                maxFare += 7.30;
                break;
            case "Stop2":
                maxFare += 5.50;
                break;
            case "Stop3":
                maxFare += 7.30;
                break;
            default:
                LOGGER.severe("stop input data incorrect");
        }

        return maxFare;
    }
}
