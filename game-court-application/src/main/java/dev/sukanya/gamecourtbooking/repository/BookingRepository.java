package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findAllByUser_Id(long userId);

    Booking findBookingByBookedDateAndCourt_Location_IdAndCourt_Game_IdAndTimeSlot_Id(Date bookedDate,int locationId, int gameId, int timeSlotId);
}
