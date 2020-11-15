package model;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class StopPair {
    String firstStop;
    String secondStop;

    public String getFirstStop() {
        return firstStop;
    }

    public void setFirstStop(String firstStop) {
        this.firstStop = firstStop;
    }

    public String getSecondStop() {
        return secondStop;
    }

    public void setSecondStop(String secondStop) {
        this.secondStop = secondStop;
    }

    //TODO : make this an enum
    public double getPairing(String firstStop, String secondStop) {
        String pair = firstStop + secondStop;
        double fare = 0.0;

        if (firstStop.equals(secondStop)){
            return  fare;
        }

        if (pair.contains("1") && pair.contains("2")){
            LOGGER.info("stop one and two");
            fare += 3.25;
            LOGGER.info("fare for one and two: " + fare);
        }

        if (pair.contains("2") && pair.contains("3")) {
            fare += 5.50;
        }

        if (pair.contains("1") && pair.contains("3")){
            fare += 7.30;
        }

        LOGGER.info("fare: " + fare);
        return fare;
    }

    public StopPair(String firstStop, String secondStop) {
        this.firstStop = firstStop;
        this.secondStop = secondStop;
    }
}
