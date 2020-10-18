package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.court.CourtDTO;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotResponseDTO;
import dev.sukanya.gamecourtbooking.model.courts.Court;
import dev.sukanya.gamecourtbooking.repository.CourtRepository;
import dev.sukanya.gamecourtbooking.repository.TimeSlotRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service    //Service says Spring Boot that this is a bean and make sure this bean is created
@Transactional
public class CourtServiceImpl implements CourtService {
    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public List<Court> getAllCourtsByCity(String city) {
        return courtRepository.findCourtByLocation_City( city);
    }

    @Override
    public List<Court> getAllCourtsByState(String state) {
        return courtRepository.findCourtByLocation_State( state);
    }


    @Override
    public List<Court> getAllCourtsByCountry(String country) {
        return courtRepository.findCourtByLocation_Country( country);
    }
    @Override
    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    @Override
    public List<TimeSlotResponseDTO> getTimeSlotsOfCourt(int courtId) {
        List<Object[]> dbResponse = timeSlotRepository.findTimeSlotsOfCourt(courtId);

        List<TimeSlotResponseDTO>  result = new ArrayList<>();
        for(Object[] timeSlot : dbResponse){
            TimeSlotResponseDTO timeSlotResponseDTO = new TimeSlotResponseDTO(timeSlot);
            result.add(timeSlotResponseDTO);
        }
        return result;
    }

    @Override
    public Court addCourt(CourtDTO courtDTO) {

        Court courtForDB = new Court();
        courtForDB.setCourtName(courtDTO.getCourtName());
        courtForDB.setLocation(courtDTO.getLocation());
        courtForDB.setGame(courtDTO.getGame());
        courtForDB.setTimeSlots(courtDTO.getTimeSlots());

        Court savedCourt = courtRepository.save(courtForDB); //This savedGame object will have ID saved in DB

        return savedCourt;
    }

    public List<Court> getCourtsByGameName(String gameName) {

        return courtRepository.findCourtsByGame_GameName( gameName);
    }
}
