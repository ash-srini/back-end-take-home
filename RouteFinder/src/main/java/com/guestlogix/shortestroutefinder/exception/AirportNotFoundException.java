package com.guestlogix.shortestroutefinder.exception;

/**
 * @author aishwaryasrinivasan
 */
public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(String message){
        super(message);
    }
}
