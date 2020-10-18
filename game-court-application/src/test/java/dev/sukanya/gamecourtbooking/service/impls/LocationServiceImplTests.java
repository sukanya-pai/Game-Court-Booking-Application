package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.location.LocationDTO;
import dev.sukanya.gamecourtbooking.exceptions.LocationAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Location;
import dev.sukanya.gamecourtbooking.repository.LocationRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.LocationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest

public class LocationServiceImplTests {

    @Autowired
    private LocationService locationService;

    @MockBean
    private LocationRepository locationRepository;

    private static Location location;

    @BeforeAll
    public static void createDummies(){
        location = new Location();
        location.setId(5);
        location.setPinCode("560047");
        location.setCountry("India");
        location.setState("Karnataka");
        location.setCity("Bangalore");
    }

    @Test
    @DisplayName("Test add Location Success")
    public void testAddLocationSuccess() throws Exception {

        doReturn(location).when(locationRepository).save(any());

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setPinCode("560047");
        locationDTO.setCountry("India");
        locationDTO.setState("Karnataka");
        locationDTO.setCity("Bangalore");
        Location locationDB = locationService.addLocation(locationDTO);
        assertThat(locationDB.getId()).isEqualTo(location.getId());
    }

    @Test
    @DisplayName("Test add Location Failure")
    public void testAddLocationFailure() throws Exception {

        doReturn(location).when(locationRepository).findLocationByPinCode(any());

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setPinCode("560047");
        locationDTO.setCountry("India");
        locationDTO.setState("Karnataka");
        locationDTO.setCity("Bangalore");

        String actualMessage="";
        try{
            Location locationDB = locationService.addLocation(locationDTO);
        }
        catch(LocationAlreadyExistsException locationAlreadyExistsException){
            actualMessage = locationAlreadyExistsException.getMessage();
        }

        assertThat(actualMessage.equals("Location already exists!"));
    }

    @Test
    @DisplayName("Test add Multiple Locations")
    public void testAddMultipleLocations() throws Exception {

        //mock the method
        doReturn(location).when(locationRepository).save(any());

        List<LocationDTO> locations = new ArrayList<>();
        LocationDTO location1 = new LocationDTO();
        location1.setPinCode("560047");
        location1.setCountry("India");
        location1.setState("Karnataka");
        location1.setCity("Bangalore");

        LocationDTO location2 = new LocationDTO();
        location2.setPinCode("560049");
        location2.setCountry("India");
        location2.setState("Karnataka");
        location2.setCity("Bangalore");

        locations.add(location1);
        locations.add(location2);
        List<String> statuses = locationService.addMultipleLocations(locations);
        assertThat(statuses.size()).isEqualTo(2);
    }
}
