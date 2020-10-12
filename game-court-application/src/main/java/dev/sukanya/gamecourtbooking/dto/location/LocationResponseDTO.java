package dev.sukanya.gamecourtbooking.dto.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponseDTO {
    private int id;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    private String message;

    public LocationResponseDTO(int id, String city, String state, String country, String pinCode,String message) {
        this.id = id;
        this.city= city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.message = message;
    }
}
