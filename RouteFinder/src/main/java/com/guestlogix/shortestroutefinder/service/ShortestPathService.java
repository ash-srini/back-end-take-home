package com.guestlogix.shortestroutefinder.service;

import com.guestlogix.shortestroutefinder.exception.NoRouteFoundException;
import com.guestlogix.shortestroutefinder.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author aishwaryasrinivasan
 * ShortestPathService uses Breadth First Search algorithm to find the shortest path between two airports
 */
@Service
public class ShortestPathService {
    Logger logger = LoggerFactory.getLogger(ShortestPathService.class);

    public List<Airport> calculateShortestPath(Airport source, Airport destination) throws NoRouteFoundException {
        logger.info("Calculating shortest path");

        Queue<Airport> airportQueue = new LinkedList<>();
        airportQueue.add(source);

        HashSet<Airport> visitedAirports = new HashSet<>();
        visitedAirports.add(source);
        //List of airports in the final shortest route
        List<Airport> route = calculate(airportQueue, visitedAirports, destination, source);
        return route;
    }

    private List<Airport> calculate(Queue<Airport> airportQueue, HashSet<Airport> visitedAirports, Airport destination, Airport source) throws NoRouteFoundException {
        // Calculates the shortest path using BFS
        List<Airport> route = new ArrayList<>();
        //Map is used to track the previous airport form which current airport was reached
        Map<Airport, Airport> connectionMap = new HashMap<>();

        while(!airportQueue.isEmpty()){
            Airport current =  airportQueue.remove();
            if(current.equals(destination)){
                Airport node = destination;
                while(node != null){
                    route.add(node);
                    node = connectionMap.get(node);
                }
                Collections.reverse(route);
            }

            List<Airport> connections = current.getConnectedAirports();
            if(connections == null){
                continue;
            }
            for(int i=0; i<connections.size(); i++){
                Airport nextHop = connections.get(i);
                if(!visitedAirports.contains(nextHop)){
                    connectionMap.put(nextHop, current);
                    airportQueue.add(nextHop);
                    visitedAirports.add(nextHop);

                }
            }
        }
        if(route.isEmpty()){
            //throw no route to destination from given source
            logger.info("No route found for given inputs source: {} and destination: {}", source.getIata3(), destination.getIata3());
            throw new NoRouteFoundException("There is no route from given source to destination");
        }
        return route;
    }
}
