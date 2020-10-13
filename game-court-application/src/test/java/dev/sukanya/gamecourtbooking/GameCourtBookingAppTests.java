package dev.sukanya.gamecourtbooking;

import dev.sukanya.gamecourtbooking.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GameCourtBookingAppTests {
    @Autowired
    private BookingController bookingController;

    @Autowired
    private CourtController courtController;

    @Autowired
    private GameController gameController;

    @Autowired
    private LocationController locationController;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private TimeSlotController timeSlotController;

    /**
     * Asserts whether the context is creating bookingController
      */
    @Test
    public void bookingControllerContextLoads() throws Exception {
        assertThat(bookingController).isNotNull();
    }

    /**
     * Asserts whether the context is creating courtController
     */
    @Test
    public void courtControllerContextLoads() throws Exception {
        assertThat(courtController).isNotNull();
    }

    /**
     * Asserts whether the context is creating gameController
     */
    @Test
    public void gameControllerContextLoads() throws Exception {

        assertThat(gameController).isNotNull();

    }

    /**
     * Asserts whether the context is creating locationController
     */
    @Test
    public void locationControllerContextLoads() throws Exception {

        assertThat(locationController).isNotNull();

    }

    /**
     * Asserts whether the context is creating registrationController
     */
    @Test
    public void registrationControllerContextLoads() throws Exception {
        assertThat(registrationController).isNotNull();
    }

    /**
     * Asserts whether the context is creating timeSlotController
     */
    @Test
    public void timeSlotControllerContextLoads() throws Exception {
        assertThat(timeSlotController).isNotNull();
    }


}


