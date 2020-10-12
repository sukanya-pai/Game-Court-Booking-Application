package dev.sukanya.gamecourtbooking.dto.booking;

import dev.sukanya.gamecourtbooking.dto.user.UserResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {

    private int bookindId;

    private String bookingName;

    private UserResponseDTO user;

    private String courtName;

    private String address;

    private TimeSlot timeSlot;

    public BookingResponseDTO(int bookindId, String bookingName, UserResponseDTO user, String courtName, String address, TimeSlot timeSlot) {
        this.bookindId = bookindId;
        this.bookingName = bookingName;
        this.user = user;
        this.courtName = courtName;
        this.address = address;
        this.timeSlot = timeSlot;
    }
}
