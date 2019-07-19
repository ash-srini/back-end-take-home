package com.guestlogix.shortestroutefinder.controller;

import com.guestlogix.shortestroutefinder.api.RouteRequest;
import com.guestlogix.shortestroutefinder.api.RouteResponse;
import com.guestlogix.shortestroutefinder.exception.AirportNotFoundException;
import com.guestlogix.shortestroutefinder.exception.NoRouteFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.guestlogix.shortestroutefinder.service.RouteService;

/**
 * @author aishwaryasrinivasan
 */
@RequestMapping("/route")
@RestController
public class RouteFinderController {
    Logger logger = LoggerFactory.getLogger(RouteFinderController.class);

    @Autowired
    private RouteService routeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<RouteResponse> retrieveShortestRoute(@RequestParam(name= "origin") String origin, @RequestParam(name= "destination") String destination) throws AirportNotFoundException, NoRouteFoundException {
        logger.info("Received route request for origin: {} and destination: {}", origin, destination);
        RouteRequest request = new RouteRequest(origin, destination);
        RouteResponse response = new RouteResponse(routeService.getRoute(request));
        logger.info("Sending route response {} for source: {} and destination: {}", response.toString(), origin, destination);
        return ResponseEntity.accepted().body(response);
    }

    @ExceptionHandler(value = AirportNotFoundException.class)
    public ResponseEntity<Object> handleInvalidInputException(AirportNotFoundException exception){
        logger.info("Invalid input Exception {}", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoRouteFoundException.class)
    public ResponseEntity<Object> handleNoROuteFound(NoRouteFoundException exception){
        logger.info("No route found to destination {}", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ACCEPTED);
    }

}
