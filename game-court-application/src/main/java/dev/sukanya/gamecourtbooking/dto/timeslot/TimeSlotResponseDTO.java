package dev.sukanya.gamecourtbooking.dto.timeslot;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class TimeSlotResponseDTO {
    private int id;
    private Time startDate;

    private Time endDate;

    public TimeSlotResponseDTO(Object[] timeSlotObject){
        this.id = (int) timeSlotObject[0];
        this.startDate = (Time) timeSlotObject[1];
        this.endDate = (Time) timeSlotObject[2];
    }
}
