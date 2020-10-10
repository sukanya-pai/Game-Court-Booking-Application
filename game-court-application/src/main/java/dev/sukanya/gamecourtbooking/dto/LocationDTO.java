package dev.sukanya.gamecourtbooking.dto;

import dev.sukanya.gamecourtbooking.annotations.PinCodeConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class LocationDTO {
    private String city;

    private String state;

    private String country;

    @PinCodeConstraint
    private String pinCode;
}
