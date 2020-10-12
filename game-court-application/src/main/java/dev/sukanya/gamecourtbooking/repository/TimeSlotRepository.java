package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {

    @Query(value="SELECT u.id, u.start_date, u.end_date FROM timeslot as u WHERE u.id IN"
            + " (SELECT r.timeslot_id FROM court_timeslot as r WHERE r.court_id = ?1)", nativeQuery=true)
    List<Object[]> findTimeSlotsOfCourt(int courtId);
}
