package com.guestlogix.shortestroutefinder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guestlogix.shortestroutefinder.api.RouteRequest;
import com.guestlogix.shortestroutefinder.exception.AirportNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

/**
 * @author aishwaryasrinivasan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RouteFinderControllerTest {

    private MockMvc mvc;

    @Autowired
    private RouteFinderController routeFinderController;


    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(routeFinderController)
                .build();
    }

    @Test
    public void testRetrieveShortestRouteValidDestination() throws Exception{

        mvc.perform(MockMvcRequestBuilders
                .get("/route?origin=YYZ&destination=JFK")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

    }

    @Test(expected = AirportNotFoundException.class)
    public void testRetrieveShortestRouteInvalidDestination() throws Exception{
        RouteRequest request = new RouteRequest("JFK", "ABC");
        when(routeFinderController.retrieveShortestRoute("JFK", "ABC")).thenThrow(new AirportNotFoundException("Airport not found"));
        mvc.perform(MockMvcRequestBuilders
                .get("/route?origin=YYZ&destination=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
