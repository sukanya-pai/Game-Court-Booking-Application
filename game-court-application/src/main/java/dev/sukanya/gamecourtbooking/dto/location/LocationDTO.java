package dev.sukanya.gamecourtbooking.dto.location;

import dev.sukanya.gamecourtbooking.annotations.PinCodeConstraint;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LocationDTO {
    private String city;

    private String state;

    private String country;

    @PinCodeConstraint
    private String pinCode;
}
