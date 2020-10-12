package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.location.LocationDTO;
import dev.sukanya.gamecourtbooking.exceptions.LocationAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Location;

import java.util.List;

public interface LocationService {
    Location addLocation(LocationDTO locationDTO) throws LocationAlreadyExistsException;

    List<String> addMultipleLocations(List<LocationDTO> locationDTOs);
}
