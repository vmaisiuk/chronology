package com.andersen.chronology.trips.exception;

import com.andersen.chronology.exception.commons.NotFoundException;

public class TripNotFoundException extends NotFoundException {

    public TripNotFoundException(String message) {
        super(message);
    }
}
