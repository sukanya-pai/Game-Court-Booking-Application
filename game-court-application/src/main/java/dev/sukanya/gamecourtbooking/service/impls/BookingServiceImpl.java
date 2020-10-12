package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.dto.user.UserResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.Booking;
import dev.sukanya.gamecourtbooking.repository.BookingRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingResponseDTO bookCourt(BookingDTO record) {
        Booking booking = new Booking();
        booking.setUser(record.getUser());
        booking.setBookingName(record.getBookingName());
        booking.setCourt(record.getCourt());
        booking.setTimeSlot(record.getTimeSlot());

        Booking bookingFromDB = bookingRepository.save(booking);
        BookingResponseDTO bookingResponseDTO = convertToBookingResponseDTO(booking);

        return bookingResponseDTO;
    }

    @Override
    public List<BookingResponseDTO> showAllBookingsForUser(long userId) {
        List<Booking> bookings = bookingRepository.findAllByUser_Id(userId);
        List<BookingResponseDTO> bookingResponseDTOS = new ArrayList<>();
        for(Booking booking:bookings){
            BookingResponseDTO bookingResponseDTO = convertToBookingResponseDTO(booking);
            bookingResponseDTOS.add(bookingResponseDTO);
        }
        return bookingResponseDTOS;
    }

    private BookingResponseDTO convertToBookingResponseDTO(Booking bookingFromDB){
        String courtLocation = bookingFromDB.getCourt().getLocation().getCity() +
                " ,"+bookingFromDB.getCourt().getLocation().getState()+
                " ,"+bookingFromDB.getCourt().getLocation().getCountry()+
                " ,"+bookingFromDB.getCourt().getLocation().getPinCode();
        UserResponseDTO userResponseDTO = new UserResponseDTO(bookingFromDB.getUser().getId(),bookingFromDB.getUser().getEmail(), bookingFromDB.getUser().getFullName(), bookingFromDB.getUser().isActive());
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(
                bookingFromDB.getId(), bookingFromDB.getBookingName(),userResponseDTO , bookingFromDB.getCourt().getCourtName(), courtLocation, bookingFromDB.getTimeSlot()
        );
        return bookingResponseDTO;
    }
}
