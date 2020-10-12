package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    TimeSlot addTimeSlot(TimeSlotDTO timeSlotDTO);

    List<String> addMultipleTimeSlots(List<TimeSlotDTO> timeSlotDTOs);
}
