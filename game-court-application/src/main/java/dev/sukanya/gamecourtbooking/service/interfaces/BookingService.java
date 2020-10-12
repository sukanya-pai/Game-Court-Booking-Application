package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;

import java.util.List;


public interface BookingService {

    BookingResponseDTO bookCourt(BookingDTO record);

    List<BookingResponseDTO> showAllBookingsForUser(long userId);

}
