package dev.sukanya.gamecourtbooking.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationAlreadyExistsException extends Exception{

    public LocationAlreadyExistsException(String message){
        super(message);
    }
}
