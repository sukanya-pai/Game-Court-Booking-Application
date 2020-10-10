package dev.sukanya.gamecourtbooking.service;

import dev.sukanya.gamecourtbooking.dto.GameDTO;
import dev.sukanya.gamecourtbooking.dto.LocationDTO;
import dev.sukanya.gamecourtbooking.exceptions.GameAlreadyExistsException;
import dev.sukanya.gamecourtbooking.exceptions.LocationAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Location;

import java.util.List;

public interface LocationService {
    Location addLocation(LocationDTO locationDTO) throws LocationAlreadyExistsException;

    List<String> addMultipleLocations(List<LocationDTO> locationDTOs);
}
