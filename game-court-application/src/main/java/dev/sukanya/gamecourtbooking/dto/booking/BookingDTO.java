package dev.sukanya.gamecourtbooking.dto.booking;

import dev.sukanya.gamecourtbooking.model.courts.Court;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {
    private String bookingName;

    private User user;

    private Court court;

    private TimeSlot timeSlot;

}
