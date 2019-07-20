package com.guestlogix.shortestroutefinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aishwaryasrinivasan
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(String message){
        super(message);
    }
}
