package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.GameContextDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingDTO;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.*;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.repository.BookingRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class BookingServiceImplTests {
    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    @DisplayName("Test book court Success")
    public void testBookCourt() throws Exception {
        BookingDTO bookingDTO = createBookingDTODummy();
        Booking booking = createBookingDummy();
        doReturn(booking).when(bookingRepository).save(any());

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

        gameContextDTO.setCourtId(6);

        gameContextDTO.setUserId(1L);

        gameContextDTO.setTimeSlotId(5);

        gameContextDTO.setGameId(2);

        record.setGameContextDTO(gameContextDTO);
        return record;
    }

}
