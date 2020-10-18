package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.repository.TimeSlotRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.TimeSlotService;
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
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class TimeSlotServiceImplTests {
    @Autowired
    private TimeSlotService timeSlotService;

    @MockBean
    private TimeSlotRepository timeSlotRepository;

    private static TimeSlot timeSlot;

    @BeforeAll
    public static void createDummies(){
        timeSlot = new TimeSlot();
        timeSlot.setStartDate(Time.valueOf("00:00:00"));
        timeSlot.setEndDate(Time.valueOf("01:00:00"));
        timeSlot.setId(3);
    }

    @Test
    @DisplayName("Test add Time Slot Success")
    public void testAddTimeSlotSuccess() throws Exception {

        doReturn(timeSlot).when(timeSlotRepository).save(any());

        TimeSlotDTO timeSlotDTO = new TimeSlotDTO();
        timeSlotDTO.setStartDate(Time.valueOf("00:00:00"));
        timeSlotDTO.setEndDate(Time.valueOf("01:00:00"));

        TimeSlot timeSlotDB = timeSlotService.addTimeSlot(timeSlotDTO);
        assertThat(timeSlotDB.getId()).isEqualTo(timeSlot.getId());
    }



    @Test
    @DisplayName("Test add Multiple Time slots")
    public void testAddMultipleTimeSlots() throws Exception {

        //mock the method
        doReturn(timeSlot).when(timeSlotRepository).save(any());

        List<TimeSlotDTO> timeSlotDTOS = new ArrayList<>();
        TimeSlotDTO timeSlotDTO1 = new TimeSlotDTO();
        timeSlotDTO1.setStartDate(Time.valueOf("00:00:00"));
        timeSlotDTO1.setEndDate(Time.valueOf("01:00:00"));

        TimeSlotDTO timeSlotDTO2 = new TimeSlotDTO();
        timeSlotDTO2.setStartDate(Time.valueOf("01:00:00"));
        timeSlotDTO2.setEndDate(Time.valueOf("02:00:00"));

        timeSlotDTOS.add(timeSlotDTO1);
        timeSlotDTOS.add(timeSlotDTO2);
        List<String> statuses = timeSlotService.addMultipleTimeSlots(timeSlotDTOS);
        assertThat(statuses.size()).isEqualTo(2);
    }
}
