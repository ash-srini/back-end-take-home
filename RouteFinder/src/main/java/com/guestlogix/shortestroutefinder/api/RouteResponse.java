package com.guestlogix.shortestroutefinder.api;

import com.guestlogix.shortestroutefinder.model.Airport;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
public class RouteResponse {
    List<String> shortestRoute;

    public RouteResponse(List<String> shortestRoute){
        this.shortestRoute = shortestRoute;
    }

    public List<String> getShortestRoute() {
        return shortestRoute;
    }

    public void setShortestRoute(List<String> shortestRoute) {
        this.shortestRoute = shortestRoute;
    }

    @Override
    public String toString(){
        return StringUtils.join(this.getShortestRoute(), " -> ");
    }
}
