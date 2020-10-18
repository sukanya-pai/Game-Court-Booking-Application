package dev.sukanya.gamecourtbooking.controller;

import dev.sukanya.gamecourtbooking.dto.*;
import dev.sukanya.gamecourtbooking.dto.location.LocationDTO;
import dev.sukanya.gamecourtbooking.dto.location.LocationResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.LocationAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Location;
import dev.sukanya.gamecourtbooking.service.interfaces.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Adds a location into the system
     * @param locationDTO
     * @param result
     * @return
     */
    @PostMapping("/location/addLocation")
    public ResponseDTO<?> addGame(@RequestBody @Valid LocationDTO locationDTO, BindingResult result){
        log.info("Received Request for adding new location ",locationDTO.getPinCode());
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }
        try{
            Location location = locationService.addLocation(locationDTO);
            return new ResponseDTO<LocationResponseDTO>(HttpStatus.OK, new LocationResponseDTO(location.getId(),location.getCity(), location.getState(), location.getCountry(), location.getPinCode(), "Location added successfully."));
        }catch( LocationAlreadyExistsException e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    /**
     * Adds multiple locations into the system
     * @param locationDTOs
     * @param result
     * @return
     */
    @PostMapping("/location/addMultipleLocations")
    public ResponseDTO<?> addMultipleGames(@RequestBody @Valid List<LocationDTO> locationDTOs, BindingResult result){
        log.info("Received Request for adding multiple locations");
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }

        try{
            List<String> statuses = locationService.addMultipleLocations(locationDTOs);
            return new ResponseDTO<List<String>>(HttpStatus.OK, statuses);
        }catch( Exception e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    public boolean checkForErrors(BindingResult result, List<String> errorMessages){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error:
                    errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}
