package com.guestlogix.shortestroutefinder.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
@JsonRootName("airport")
@JsonPropertyOrder(value = {"name", "city", "country", "iata3", "latitude", "longitude"})
public class Airport implements Serializable, Comparable<Airport> {

    private String name;
    private String city;
    private String country;
    private String iata3;
    private String latitude;
    private String longitude;
    private List<Airport> connectedAirports;
    private List<Airport> shortestPath;
    private Integer distance = Integer.MAX_VALUE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata3() {
        return iata3;
    }

    public void setIata3(String iata3) {
        this.iata3 = iata3;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Airport> getConnectedAirports() {
        return connectedAirports;
    }

    public void setConnectedAirports(List<Airport> connectedAirports) {
        this.connectedAirports = connectedAirports;
    }

    public List<Airport> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Airport> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Airport other = (Airport) obj;
        if (iata3 == null) {
            if (other.iata3 != null)
                return false;
        } else if (!iata3.equals(other.iata3))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((iata3 == null) ? 0 : iata3.hashCode());
        return result;
    }

    @Override
    public int compareTo(Airport o) {
        return connectedAirports.size();
    }
}
