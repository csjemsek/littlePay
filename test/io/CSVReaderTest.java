package io;

import model.TripPart;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    static String inputFile = "";
    static CSVReader csvReader = new CSVReader();
    static Map<String , TripPart> tripParts;
    static TripPart first;
    static TripPart second;

    @BeforeEach
    public void setup() {
        inputFile = "input.csv";
        tripParts = csvReader.read(inputFile);
        first = tripParts.get("0");
        second = tripParts.get("1");
    }

    @Test
    public void shouldNotBeNullObject() {

        //Assert that objects are not null
        tripParts.forEach((k,v) -> assertNotNull(v));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC()));

        tripParts.forEach((k,v) -> assertNotNull(v.getTapType()));

        tripParts.forEach((k,v) -> assertNotNull(v.getStopId()));

        tripParts.forEach((k,v) -> assertNotNull(v.getPan()));

        tripParts.forEach((k,v) -> assertNotNull(v.getiD()));

        tripParts.forEach((k,v) -> assertNotNull(v.getCompanyId()));

        tripParts.forEach((k,v) -> assertNotNull(v.getBusId()));

    }

    @Test
    public void shouldParseDate() {
        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getSecond()));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getMinute()));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getHour()));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getDayOfWeek()));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getMonth()));

        tripParts.forEach((k,v) -> assertNotNull(v.getDateTimeUTC().getYear()));

    }
}