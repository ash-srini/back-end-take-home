package com.guestlogix.shortestroutefinder.service;

import com.guestlogix.shortestroutefinder.api.RouteRequest;
import com.guestlogix.shortestroutefinder.exception.NoRouteFoundException;
import com.guestlogix.shortestroutefinder.model.Airport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShortestPathServiceTest {

    List<Airport> airportList = new ArrayList<>();

    @Autowired
    ShortestPathService shortestPathService;

    @Autowired
    RouteFinderService routeFinderService;

    Airport jfk;
    Airport yyz;
    Airport lax;
    Airport yvr;
    Airport ord;


    @Before
    public void setup(){
        jfk = new Airport("John F Kennedy International Airport", "New York", "United States", "JFK", "40.63980103", "-73.77890015");
        yyz = new Airport("Lester B. Pearson International Airport", "Toronto", "Canada", "YYZ","43.67720032","-79.63059998");
        lax = new Airport("Los Angeles International Airport", "Los Angeles", "United States", "LAX", "33.94250107","-118.4079971");
        yvr = new Airport("Vancouver International Airport", "Vancouver", "Canada", "YVR", "49.19390106","-123.1839981");
        ord = new Airport("Chicago O'Hare International Airport", "Chicago", "United States", "ORD", "41.97859955","-87.90480042");

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
    public void shortestPathSuccessTest() throws NoRouteFoundException {

        List<Airport> result = shortestPathService.calculateShortestPath(jfk, yyz);
        Assert.assertEquals(result.get(0), jfk);
        Assert.assertEquals(result.get(1), yyz);

        result = shortestPathService.calculateShortestPath(yyz, yvr);
        Assert.assertEquals(result.get(0), yyz);
        Assert.assertEquals(result.get(1), jfk);
        Assert.assertEquals(result.get(2), lax);
        Assert.assertEquals(result.get(3), yvr);

    }

    @Test
    public void shortestPathNoRouteTest(){
        NoRouteFoundException exception = Assertions.assertThrows(NoRouteFoundException.class, () -> shortestPathService.calculateShortestPath(ord, yyz),
                "Expected calculateShortestPath to throw NoRouteFoundException");
        Assert.assertTrue(exception.getMessage().equals("There is no route from given source to destination"));

    }
}
