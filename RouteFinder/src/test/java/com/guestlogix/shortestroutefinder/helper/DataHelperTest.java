package com.guestlogix.shortestroutefinder.helper;

import com.guestlogix.shortestroutefinder.exception.AirportNotFoundException;
import com.guestlogix.shortestroutefinder.model.Airport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataHelperTest {

    List<Airport> airportList = new ArrayList<>();

    @Before
    public void setupData(){
        Airport jfk = new Airport("John F Kennedy International Airport", "New York", "United States", "JFK", "40.63980103", "-73.77890015");
        Airport yyz = new Airport("Lester B. Pearson International Airport", "Toronto", "Canada", "YYZ","43.67720032","-79.63059998");
        Airport lax = new Airport("Los Angeles International Airport", "Los Angeles", "United States", "LAX", "33.94250107","-118.4079971");
        Airport yvr = new Airport("Vancouver International Airport", "Vancouver", "Canada", "YVR", "49.19390106","-123.1839981");
        Airport ord = new Airport("Chicago O'Hare International Airport", "Chicago", "United States", "ORD", "41.97859955","-87.90480042");

        airportList.add(jfk);
        airportList.add(yyz);
        airportList.add(lax);
        airportList.add(yvr);
        airportList.add(ord);

        yyz.getConnectedAirports().add(jfk);
        jfk.getConnectedAirports().add(yyz);
        lax.getConnectedAirports().add(yvr);
        yvr.getConnectedAirports().add(lax);
        lax.getConnectedAirports().add(jfk);
        jfk.getConnectedAirports().add(lax);
    }

    @Test
    public void testInvalidAirport(){
        AirportNotFoundException exception = Assertions.assertThrows(AirportNotFoundException.class,
                () ->DataHelper.validateAirports("YYZ", "PPP", airportList),
                "Expected validateAirports to throw AirportNotFoundException");
        Assert.assertTrue(exception.getMessage().equals("You have entered an invalid destination airport. Please enter a valid airport code."));

        exception = Assertions.assertThrows(AirportNotFoundException.class,
                () ->DataHelper.validateAirports("XYZ", "JFK", airportList),
                "Expected validateAirports to throw AirportNotFoundException");
        Assert.assertTrue(exception.getMessage().equals("You have entered an invalid origin airport. Please enter a valid airport code."));

    }

    @Test
    public void testFindAirport(){
        Airport airport = DataHelper.findAirportfromList("ORD", airportList);
        Assert.assertEquals(airport.getName(), "Chicago O'Hare International Airport");
        Assert.assertEquals(airport.getCity(), "Chicago");
        Assert.assertEquals(airport.getCountry(), "United States");
        Assert.assertEquals(airport.getIata3(), "ORD");
        Assert.assertEquals(airport.getLatitude(), "41.97859955");
        Assert.assertEquals(airport.getLongitude(), "-87.90480042");
    }
}
