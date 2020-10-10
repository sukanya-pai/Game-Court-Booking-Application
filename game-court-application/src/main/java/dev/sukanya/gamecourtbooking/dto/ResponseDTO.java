package dev.sukanya.gamecourtbooking.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDTO<T> {

    private T data;
    private HttpStatus status;

    public ResponseDTO(HttpStatus status, T data){
        this.data=data;
        this.status=status;
    }

    /***
     * While sending response from Controller, we follow a generic response object style
     **/
}

