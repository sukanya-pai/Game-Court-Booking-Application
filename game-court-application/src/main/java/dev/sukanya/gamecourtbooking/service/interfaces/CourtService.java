package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.court.CourtDTO;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.CourtAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Court;

import java.util.List;

public interface CourtService {

    List<Court> getAllCourtsByCity(String city);
    List<Court> getAllCourtsByState(String state);
    List<Court> getAllCourtsByCountry(String country);


    List<Court> getAllCourts();

    List<TimeSlotResponseDTO> getTimeSlotsOfCourt(int courtId);

    Court addCourt(CourtDTO court) throws CourtAlreadyExistsException;

    List<Court> getCourtsByGameName(String gameName);

    Court convertCourtDTOtoCourt(CourtDTO courtDTO);

    Court findCourtById(int courtId);

    CourtDTO convertCourtToCourtDTO( Court court);
}
