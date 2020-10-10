package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    Location findLocationByPinCode(String pinCode);
}
