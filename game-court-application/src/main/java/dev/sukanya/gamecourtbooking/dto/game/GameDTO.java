package dev.sukanya.gamecourtbooking.dto.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//DTO-> Data Transfer Object --> To need for our usage (kind of model that we used before)
@Getter
@Setter
public class GameDTO {
    private int id;
    @NotEmpty
    @NotNull
    private String gameName;

}
