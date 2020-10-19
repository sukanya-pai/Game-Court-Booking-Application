package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.GameContextDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.*;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.repository.BookingRepository;
import dev.sukanya.gamecourtbooking.repository.CourtRepository;
import dev.sukanya.gamecourtbooking.repository.TimeSlotRepository;
import dev.sukanya.gamecourtbooking.repository.UserRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class BookingServiceImplTests {
    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourtRepository courtRepository;

    @MockBean
    private TimeSlotRepository timeSlotRepository;

    static Court courtDummy;

    static User userDummy;

    static TimeSlot timeSlotDummy;
    @BeforeAll
    public static void createDummiesObject(){

        // Court dummy
        courtDummy = new Court();

        courtDummy.setCourtName("Silver String Sports");
        Location location = new Location();
        location.setId(5);
        location.setPinCode("560047");
        location.setCountry("India");
        location.setState("Karnataka");
        location.setCity("Bangalore");
        courtDummy.setLocation(location);

        Game game = new Game();
        game.setId(1);
        game.setGameName("Badminton");
        courtDummy.setGame(game);

        List<TimeSlot> timeSlots = new ArrayList<>();
        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setStartDate(Time.valueOf("00:00:00"));
        timeSlot1.setEndDate(Time.valueOf("01:00:00"));
        timeSlot1.setId(3);
        timeSlots.add(timeSlot1);

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setStartDate(Time.valueOf("01:00:00"));
        timeSlot2.setEndDate(Time.valueOf("02:00:00"));
        timeSlot2.setId(4);
        timeSlots.add(timeSlot2);
        courtDummy.setTimeSlots(timeSlots);


        // User dummy
        userDummy = new User();
        userDummy.setFullName("Sukanya Pai");
        userDummy.setId(1L);
        userDummy.setEmail("sukanyasurendrapai@gmail.com");
        userDummy.setPassword("Pass@123");

        //Time Slot Dummy
        timeSlotDummy = new TimeSlot();
        timeSlotDummy.setId(5);
        timeSlotDummy.setStartDate(Time.valueOf("03:00:00"));
        timeSlotDummy.setEndDate(Time.valueOf("04:00:00"));

        System.out.println("BEFORE ALL");
    }
    @Test
    @DisplayName("Test book court Success")
    public void testBookCourt() throws Exception {
        BookingDTO bookingDTO = createBookingDTODummy();
        Booking booking = createBookingDummy();

        //mock objects
        doReturn(booking).when(bookingRepository).save(any());
        doReturn(Optional.of(courtDummy)).when(courtRepository).findById(anyInt());
        doReturn(Optional.of(userDummy)).when(userRepository).findById(anyLong());
        doReturn(Optional.of(timeSlotDummy)).when(timeSlotRepository).findById(anyInt());


        BookingResponseDTO bookingResponseDTO = bookingService.bookCourt(bookingDTO);
        assertThat(bookingResponseDTO.getBookingId()).isEqualTo(booking.getId());
    }
    private Booking createBookingDummy(){
        Booking record = new Booking();
        record.setId(7);
        record.setBookingName("J Unit Test Book");

        Court court = new Court();
        court.setId(6);
        court.setCourtName("Silver String Sports");
        Location location = new Location();
        location.setId(5);
        location.setPinCode("560123");
        location.setCountry("India");
        location.setState("West Bengal");
        location.setCity("Kolkata");
        court.setLocation(location);

        Game game = new Game();
        game.setId(2);
        game.setGameName("Tennis");
        court.setGame(game);

        List<TimeSlot> timeSlots = new ArrayList<>();
        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setStartDate(Time.valueOf("01:00:00"));
        timeSlot1.setEndDate(Time.valueOf("02:00:00"));
        timeSlot1.setId(4);
        timeSlots.add(timeSlot1);

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setStartDate(Time.valueOf("03:00:00"));
        timeSlot2.setEndDate(Time.valueOf("04:00:00"));
        timeSlot2.setId(5);
        timeSlots.add(timeSlot2);
        court.setTimeSlots(timeSlots);

        record.setCourt(court);

        User user = new User();
        user.setFullName("Sukanya Pai");
        user.setId(1L);
        user.setEmail("sukanyasurendrapai@gmail");

        record.setUser(user);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(5);
        timeSlot.setStartDate(Time.valueOf("03:00:00"));
        timeSlot.setEndDate(Time.valueOf("04:00:00"));

        record.setTimeSlot(timeSlot);
        return record;
    }
    private BookingDTO createBookingDTODummy(){
        BookingDTO record = new BookingDTO();
        record.setBookingName("J Unit Test Book");

        GameContextDTO gameContextDTO = new GameContextDTO();

        gameContextDTO.setCourtId(4);

        gameContextDTO.setUserId(1L);

        gameContextDTO.setTimeSlotId(3);

        gameContextDTO.setGameId(2);

        record.setGameContextDTO(gameContextDTO);
        return record;
    }

}
