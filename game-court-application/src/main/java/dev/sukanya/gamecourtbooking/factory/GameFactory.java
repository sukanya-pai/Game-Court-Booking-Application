package dev.sukanya.gamecourtbooking.factory;

import dev.sukanya.gamecourtbooking.dto.game.BadmintonGameDTO;
import dev.sukanya.gamecourtbooking.dto.game.GameDTO;

public class GameFactory {

    public GameDTO getGameDTOType(String gameName){
        if(gameName == null){
            return null;
        }
        if(gameName.equalsIgnoreCase("Badminton")) {
            GameDTO badmintonGameDTO = new BadmintonGameDTO();
            //set parameters
            badmintonGameDTO.setGameName(gameName);
            return badmintonGameDTO;
        }
        else if(gameName.equalsIgnoreCase("Tennis")){
            //return new TennisGameDTO()
        }
        // so on
        return null;
    }
}
