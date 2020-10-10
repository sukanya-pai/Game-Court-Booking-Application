package dev.sukanya.gamecourtbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private String city;

    private String state;

    private String country;

    private String pinCode;
}
