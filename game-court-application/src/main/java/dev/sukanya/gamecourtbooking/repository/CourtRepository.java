package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.Court;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court,Integer> {

    List<Court> findCourtByLocation_City(String city);

    List<Court> findCourtByLocation_State(String state);

    List<Court> findCourtByLocation_Country(String country);

    List<Court> findCourtsByGame_GameName(String gameName);


}
