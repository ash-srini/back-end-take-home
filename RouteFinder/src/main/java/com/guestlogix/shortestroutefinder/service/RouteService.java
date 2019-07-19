package com.guestlogix.shortestroutefinder.service;

import com.guestlogix.shortestroutefinder.api.RouteRequest;
import com.guestlogix.shortestroutefinder.exception.NoRouteFoundException;
import com.guestlogix.shortestroutefinder.model.Airport;

import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
public interface RouteService {
    //define methods route service should implement

    public List<String> getRoute(RouteRequest request) throws NoRouteFoundException;
}
