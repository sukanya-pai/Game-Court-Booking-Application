package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.BookingAlreadyExistsException;

import java.util.List;


public interface BookingService {

    BookingResponseDTO bookCourt(BookingDTO record) throws BookingAlreadyExistsException;

    List<BookingResponseDTO> showAllBookingsForUser(long userId);

}
