package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.GameContextDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.dto.cost.CostType;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotResponseDTO;
import dev.sukanya.gamecourtbooking.dto.user.UserResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.BookingAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Booking;
import dev.sukanya.gamecourtbooking.model.courts.Court;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.repository.BookingRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import dev.sukanya.gamecourtbooking.service.interfaces.CourtService;
import dev.sukanya.gamecourtbooking.service.interfaces.TimeSlotService;
import dev.sukanya.gamecourtbooking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtService courtService;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private UserService userService;

    @Autowired
    private CostType costType;

    /**
     * The function validates if a booking already exists
     * @param record
     * @return boolean true or false
     */
    private boolean validateBookingRecord(BookingDTO record){
        int timeSlotId = record.getGameContextDTO().getTimeSlotId();
        Court court = courtService.findCourtById(record.getGameContextDTO().getCourtId());
        if(court!=null){
            int locationId = court.getLocation().getId();
            int gameId = court.getGame().getId();

            //today's date
            Date todaysDate = Date.valueOf(LocalDate.now());
            Booking booking = bookingRepository.findBookingByBookedDateAndCourt_Location_IdAndCourt_Game_IdAndTimeSlot_Id(todaysDate, locationId,gameId,timeSlotId);

            if(booking!=null){
                return false;
            }
        }
        return true;
    }

    /**
     * This function books a court based on the selected game, court and timeslot and returns the cost of the booking with details
     * @param record
     * @return BookingResponseDTO
     * @throws BookingAlreadyExistsException
     */
    @Override
    public BookingResponseDTO bookCourt(BookingDTO record) throws BookingAlreadyExistsException {
        if(!validateBookingRecord(record)){
            throw new BookingAlreadyExistsException("Booking Already Exists!");
        }


        Booking booking = new Booking();
        booking.setBookingName(record.getBookingName());
        GameContextDTO gameContextDTO = record.getGameContextDTO();
        User user = userService.findUserById(gameContextDTO.getUserId());
        booking.setUser(user);

        Court court = courtService.findCourtById(gameContextDTO.getCourtId());
        booking.setCourt(court);

        TimeSlot timeSlot = timeSlotService.findTimeSlotById(gameContextDTO.getTimeSlotId());
        booking.setTimeSlot(timeSlot);

        //algorithm to calculate cost - based on court type and time slot selected charges would be calculated
        booking.setCost(costType.costCalculationAlgorithm(court.getSpecialCourtChargesPerHour(),timeSlotService.convertTimeSlotToTimeSlotDTO(timeSlot)));

        // today's date
        booking.setBookedDate(Date.valueOf(LocalDate.now()));
        Booking bookingFromDB = bookingRepository.save(booking);
        BookingResponseDTO bookingResponseDTO = convertToBookingResponseDTO(bookingFromDB);

        return bookingResponseDTO;
    }

    /**
     * gets details of all the bookings
     * @param userId
     * @return List<BookingResponseDTO>
     */
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
                bookingFromDB.getId(), bookingFromDB.getBookingName(),userResponseDTO , bookingFromDB.getCourt().getCourtName(), courtLocation, convertToTimeSlotResponseDTO(bookingFromDB.getTimeSlot()),bookingFromDB.getCost()
        );
        return bookingResponseDTO;
    }

    private TimeSlotResponseDTO convertToTimeSlotResponseDTO(TimeSlot timeSlot){
        return  new TimeSlotResponseDTO(timeSlot.getId(),timeSlot.getStartDate(),timeSlot.getEndDate());
    }
}
