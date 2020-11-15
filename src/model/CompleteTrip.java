package model;

public class CompleteTrip {
    TripPart tripPart1;
    TripPart tripPart2;

    double duration;
    double chargeAmount;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    Status status;
    public enum Status{
        COMPLETE,
        INCOMPLETE,
        UNDEFINED
    }


    public TripPart getTripPart1() {
        return tripPart1;
    }

    public void setTripPart1(TripPart tripPart1) {
        this.tripPart1 = tripPart1;
    }

    public TripPart getTripPart2() {
        return tripPart2;
    }

    public void setTripPart2(TripPart tripPart2) {
        this.tripPart2 = tripPart2;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public CompleteTrip() {

    }
}
