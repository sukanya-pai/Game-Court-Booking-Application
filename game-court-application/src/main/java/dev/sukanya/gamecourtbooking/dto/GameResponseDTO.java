package dev.sukanya.gamecourtbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponseDTO {
    private int id;

    private String gameName;

    private String message;

    public GameResponseDTO(int id, String gameName, String message) {
        this.id = id;
        this.gameName= gameName;
        this.message = message;
    }
}
