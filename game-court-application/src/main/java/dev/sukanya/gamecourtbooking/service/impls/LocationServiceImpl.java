package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.location.LocationDTO;
import dev.sukanya.gamecourtbooking.exceptions.LocationAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Location;
import dev.sukanya.gamecourtbooking.repository.LocationRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location addLocation(LocationDTO locationDTO) throws LocationAlreadyExistsException {
        Location locationDB = locationRepository.findLocationByPinCode(locationDTO.getPinCode());
        if(locationDB!=null && locationDB.getPinCode().equalsIgnoreCase(locationDTO.getPinCode())){
            //Game already exists, throw exception
            throw new LocationAlreadyExistsException("Location of PinCode already exists!");
        }

        Location locationForDB = new Location();
        locationForDB.setCity(locationDTO.getCity());
        locationForDB.setState(locationDTO.getState());
        locationForDB.setCountry(locationDTO.getCountry());
        locationForDB.setPinCode(locationDTO.getPinCode());

        Location savedLocation = locationRepository.save(locationForDB); //This savedGame object will have ID saved in DB

        return savedLocation;
    }

    @Override
    public List<String> addMultipleLocations(List<LocationDTO> locationDTOs) {
        List<String> statuses = new ArrayList<>();
        for(LocationDTO location : locationDTOs){
            try{
                Location locationFromDB = addLocation(location);
                statuses.add(locationFromDB.getCity() + ", "+locationFromDB.getPinCode()+" successfully added.");
            }
            catch(LocationAlreadyExistsException locationAlreadyExistsException){
                statuses.add(location.getCity() + ", "+location.getPinCode()+"  already exists.");
            }
        }
        return statuses;
    }
}
