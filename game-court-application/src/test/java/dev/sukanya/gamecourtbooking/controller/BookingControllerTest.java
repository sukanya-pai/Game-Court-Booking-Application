package dev.sukanya.gamecourtbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sukanya.gamecourtbooking.dto.booking.BookingResponseDTO;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotResponseDTO;
import dev.sukanya.gamecourtbooking.dto.user.UserResponseDTO;
import dev.sukanya.gamecourtbooking.service.interfaces.BookingService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookingService bookingService;

    private List<BookingResponseDTO> bookingList;

    @BeforeEach
    void setUp() {
        this.bookingList = new ArrayList<>();

        this.bookingList.add(new BookingResponseDTO(4, "Sukanya's Booking",
                                        new UserResponseDTO(1L,"sukanyasurendrapai@gmail.com","Sukanya Pai", false),
                                        "Silver String Sports","Kolkata ,West Bengal ,India ,560123",
                                        new TimeSlotResponseDTO(5,Time.valueOf("03:00:00"),Time.valueOf("04:00:00"))));
        this.bookingList.add(new BookingResponseDTO(5, "Sukanya's Booking",
                new UserResponseDTO(1L,"sukanyasurendrapai@gmail.com","Sukanya Pai", false),
                "Silver String Sports","Kolkata ,West Bengal ,India ,560123",
                new TimeSlotResponseDTO(5,Time.valueOf("03:00:00"),Time.valueOf("04:00:00"))));
        this.bookingList.add(new BookingResponseDTO(6, "Sukanya's Booking",
                new UserResponseDTO(1L,"sukanyasurendrapai@gmail.com","Sukanya Pai", false),
                "Silver String Sports","Kolkata ,West Bengal ,India ,560123",
                new TimeSlotResponseDTO(5,Time.valueOf("03:00:00"),Time.valueOf("04:00:00"))));

        this.bookingList.add(new BookingResponseDTO(7, "Sukanya's Booking",
                new UserResponseDTO(1L,"sukanyasurendrapai@gmail.com","Sukanya Pai", false),
                "Silver String Sports","Kolkata ,West Bengal ,India ,560123",
                new TimeSlotResponseDTO(5,Time.valueOf("03:00:00"),Time.valueOf("04:00:00"))));

//        objectMapper.registerModule(new ProblemModule());
//        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    @Test
    public void testGetAllBookings() throws Exception {

        given(bookingService.showAllBookingsForUser(1L)).willReturn(bookingList);

        this.mockMvc.perform(get("/api/booking/allbookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookingList.size())));
    }


}