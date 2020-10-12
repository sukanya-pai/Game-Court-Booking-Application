package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.game.GameDTO;
import dev.sukanya.gamecourtbooking.exceptions.GameAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Game;

import java.util.List;

public interface GameService {
    Game addGame(GameDTO gameDTO) throws GameAlreadyExistsException;

    List<String> addMultipleGames(List<GameDTO> gameDTOs);
}
