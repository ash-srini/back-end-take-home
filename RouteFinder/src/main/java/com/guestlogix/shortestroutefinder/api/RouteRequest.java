package com.guestlogix.shortestroutefinder.api;

/**
 * @author aishwaryasrinivasan
 */
public class RouteRequest {

    private String origin;
    private String destination;

    public RouteRequest(String origin, String destination){
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
