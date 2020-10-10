package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {

}
