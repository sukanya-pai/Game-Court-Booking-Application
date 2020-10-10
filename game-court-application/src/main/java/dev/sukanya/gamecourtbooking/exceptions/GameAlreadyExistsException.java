package dev.sukanya.gamecourtbooking.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameAlreadyExistsException extends Exception{

    public GameAlreadyExistsException(String message){
        super(message);
    }
}
