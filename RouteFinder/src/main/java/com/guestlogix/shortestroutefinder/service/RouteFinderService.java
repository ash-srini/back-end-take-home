package com.guestlogix.shortestroutefinder.service;

import com.guestlogix.shortestroutefinder.api.RouteRequest;
import com.guestlogix.shortestroutefinder.exception.NoRouteFoundException;
import com.guestlogix.shortestroutefinder.helper.DataHelper;
import com.guestlogix.shortestroutefinder.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
@Service
public class RouteFinderService implements RouteService {

    Logger logger = LoggerFactory.getLogger(RouteFinderService.class);

    @Autowired
    List<Airport> airportList;
    @Autowired
    ShortestPathService shortestPathService;

    public RouteFinderService(){
    }

    @Override
    public List<String> getRoute(RouteRequest request) throws NoRouteFoundException {
        logger.info("Finding route for source: {} and destination: {}", request.getOrigin(), request.getDestination());
        DataHelper.validateAirports(request.getOrigin(), request.getDestination(), airportList);
        Airport source = DataHelper.findAirportfromList(request.getOrigin(), airportList);
        Airport destination = DataHelper.findAirportfromList(request.getDestination(), airportList);
        List<Airport> route = shortestPathService.calculateShortestPath(source, destination);
        return getRouteIataCode(route);
    }

    private List<String> getRouteIataCode(List<Airport> route){
        List<String> finalRoute = new ArrayList<>();
        for(Airport airport : route){
            finalRoute.add(airport.getIata3());
        }
        return finalRoute;
    }





}
