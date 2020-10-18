package dev.sukanya.gamecourtbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameContextDTO {
    private long userId;
    private int gameId;
    private int courtId;
    private int timeSlotId;
}
