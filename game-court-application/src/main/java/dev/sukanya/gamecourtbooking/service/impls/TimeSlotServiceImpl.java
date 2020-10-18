package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.repository.TimeSlotRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Component
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public TimeSlot addTimeSlot(TimeSlotDTO timeSlotDTO) {
        TimeSlot timeSlotForDB = new TimeSlot();
        timeSlotForDB.setStartDate(timeSlotDTO.getStartDate());
        timeSlotForDB.setEndDate(timeSlotDTO.getEndDate());

        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlotForDB); //This savedGame object will have ID saved in DB

        return savedTimeSlot;
    }

    @Override
    public List<String> addMultipleTimeSlots(List<TimeSlotDTO> timeSlotDTOs) {
        List<String> status = new ArrayList<>();
       for(TimeSlotDTO timeSlotDTO: timeSlotDTOs){
           TimeSlot savedTimeSlot = addTimeSlot(timeSlotDTO);
           status.add(savedTimeSlot.getStartDate() +"& "+savedTimeSlot.getEndDate()+ " - Time Slot added successfully.");
       }
       return status;
    }
}
