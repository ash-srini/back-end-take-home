package com.guestlogix.shortestroutefinder.helper;

import com.guestlogix.shortestroutefinder.exception.AirportNotFoundException;
import com.guestlogix.shortestroutefinder.model.Airport;

import java.util.List;

/**
 * @author aishwaryasrinivasan
 *
 * DataHelper is a helper class used to setup, retrieve and validate data for the application
 */
public class DataHelper {

    // Given the IATA3 code this method finds the corresponding airport
    public static Airport findAirportfromList(String iata3, List<Airport> airportList){
        for(Airport airport : airportList){
            if(airport.getIata3().equalsIgnoreCase(iata3)){
                return airport;
            }
        }
        return null;
    }
    // Given the IATA3 code this method validates if the airport with the code exists
    public static void validateAirports(String iataSource, String iataDestination, List<Airport> airportList) throws AirportNotFoundException {
        boolean isSourcePresent = false;
        boolean isDestinationPresent = false;
        for(Airport airport : airportList){
            if(airport.getIata3().equalsIgnoreCase(iataSource)){
                isSourcePresent = true;
            }
            if(airport.getIata3().equalsIgnoreCase(iataDestination)){
                isDestinationPresent = true;
            }
        }
        if(!isSourcePresent){
            throw new AirportNotFoundException("You have entered an invalid origin airport. Please enter a valid airport code.");

        }
        if(!isDestinationPresent){
            throw new AirportNotFoundException("You have entered an invalid destination airport. Please enter a valid airport code.");
        }
    }
}
