package dev.sukanya.gamecourtbooking.dto.booking;

import dev.sukanya.gamecourtbooking.dto.GameContextDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {
    private String bookingName;

    private GameContextDTO gameContextDTO;

}
