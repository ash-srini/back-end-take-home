package com.guestlogix.shortestroutefinder.model;

/**
 * @author aishwaryasrinivasan
 */

public class Route {

    private String start;
    private String end;
    private String airlineId;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }
}
