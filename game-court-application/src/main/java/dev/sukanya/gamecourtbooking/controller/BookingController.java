package dev.sukanya.gamecourtbooking.controller;

import dev.sukanya.gamecourtbooking.dto.ResponseDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * This is used for booking a court by passing details of selected court id, gameid, timeslotid, booking name.
     * @param bookingDTO
     * @param result
     * @return If sucessfull, returns the booking details along with cost.
     */
    @PostMapping("api/booking-management/book-court")
    public ResponseDTO<?> createBooking(@RequestBody @Valid BookingDTO bookingDTO, BindingResult result){
        log.info("Received Request for booking court" );
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }
        try{
            BookingResponseDTO booking= bookingService.bookCourt(bookingDTO);

            return new ResponseDTO<BookingResponseDTO>(HttpStatus.OK, booking);
        }catch( Exception e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    /**
     * Used to get list of all the bookings.
     * @param id
     * @return List of all bookings
     */
    @GetMapping("api/booking-management/users/{id}/bookings")
    public ResponseDTO<?> getAllBookings(@PathVariable("id") long id){
        log.info("Received Request for getting all bookings of a user" );

        try{
            List<BookingResponseDTO> bookings= bookingService.showAllBookingsForUser(id);

            return new ResponseDTO<List<BookingResponseDTO>>(HttpStatus.OK, bookings);
        }catch( Exception e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    public boolean checkForErrors(BindingResult result, List<String> errorMessages){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error:
                    errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}
