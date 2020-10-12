package dev.sukanya.gamecourtbooking.dto.court;

import dev.sukanya.gamecourtbooking.model.courts.Game;
import dev.sukanya.gamecourtbooking.model.courts.Location;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourtDTO {
    private String courtName;

    private Location location;

    private Game game;

    private List<TimeSlot> timeSlots;
}
