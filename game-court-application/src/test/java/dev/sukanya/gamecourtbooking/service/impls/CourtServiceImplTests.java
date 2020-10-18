package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.court.CourtDTO;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.Court;
import dev.sukanya.gamecourtbooking.model.courts.Game;
import dev.sukanya.gamecourtbooking.model.courts.Location;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.repository.CourtRepository;
import dev.sukanya.gamecourtbooking.repository.TimeSlotRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.CourtService;
import org.junit.jupiter.api.BeforeAll;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CourtServiceImplTests {

    @Autowired
    private CourtService courtService;

    @MockBean
    private CourtRepository courtRepository;

    @MockBean
    private TimeSlotRepository timeSlotRepository;

    public static List<Court> courtDummies;
    public static List<Object[]> timeSlotsDummies;

    @BeforeAll
    public static void createDummiesObject(){
        courtDummies = new ArrayList<>();

        Court record = new Court();
        record.setId(5);

        record.setCourtName("Silver String Sports");
        Location location = new Location();
        location.setId(5);
        location.setPinCode("560047");
        location.setCountry("India");
        location.setState("Karnataka");
        location.setCity("Bangalore");
        record.setLocation(location);

        Game game = new Game();
        game.setId(1);
        game.setGameName("Badminton");
        record.setGame(game);

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
        record.setTimeSlots(timeSlots);

        courtDummies.add(record);

        //Dummy time slots object
        timeSlotsDummies = new ArrayList<>();
        Object[] ts1 = {3, Time.valueOf("00:00:00"),Time.valueOf("01:00:00")};
        Object[] ts2 = {4, Time.valueOf("01:00:00"),Time.valueOf("02:00:00")};

        timeSlotsDummies.add(ts1);
        timeSlotsDummies.add(ts2);

        System.out.println("BEFORE ALL");
    }


    @Test
    @DisplayName("Test Get Courts By city success")
    public void testGetCourtsByCitySuccess() throws Exception {

        doReturn(courtDummies).when(courtRepository).findCourtByLocation_City(any());
        List<Court> courts = courtService.getAllCourtsByCity("Bangalore");
        assertThat(courts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Get Courts By State success")
    public void testGetCourtsByStateSuccess() throws Exception {

        doReturn(courtDummies).when(courtRepository).findCourtByLocation_State(any());
        List<Court> courts = courtService.getAllCourtsByState("Karnataka");
        assertThat(courts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Get Courts By Country success")
    public void testGetCourtsByCountrySuccess() throws Exception {

        doReturn(courtDummies).when(courtRepository).findCourtByLocation_Country(any());
        List<Court> courts = courtService.getAllCourtsByCountry("India");
        assertThat(courts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Get All courts")
    public void testGetAllCourtsSuccess() throws Exception {

        doReturn(courtDummies).when(courtRepository).findAll();
        List<Court> courts = courtService.getAllCourts();
        assertThat(courts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Get Time Slot of court")
    public void testGetTimeSlotsOfCourtSuccess() throws Exception {


        doReturn(timeSlotsDummies).when(timeSlotRepository).findTimeSlotsOfCourt(anyInt());
        List<TimeSlotResponseDTO> timeSlots = courtService.getTimeSlotsOfCourt(5);
        assertThat(timeSlots.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test Get All courts of game")
    public void testGetAllCourtsOfGameSuccess() throws Exception {

        doReturn(courtDummies).when(courtRepository).findCourtsByGame_GameName(any());
        List<Court> courts = courtService.getCourtsByGameName("Badminton");
        assertThat(courts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Add a court")
    public void testAddCourtSuccess() throws Exception {
        doReturn(courtDummies.get(0)).when(courtRepository).save(any());

        CourtDTO courtForDB = new CourtDTO();
        courtForDB.setCourtName(courtDummies.get(0).getCourtName());
        courtForDB.setLocation(courtDummies.get(0).getLocation());
        courtForDB.setGame(courtDummies.get(0).getGame());
        courtForDB.setTimeSlots(courtDummies.get(0).getTimeSlots());
        Court courtFromDB = courtService.addCourt(courtForDB);
        assertThat(courtFromDB.getId()).isEqualTo(courtDummies.get(0).getId());
    }
}
