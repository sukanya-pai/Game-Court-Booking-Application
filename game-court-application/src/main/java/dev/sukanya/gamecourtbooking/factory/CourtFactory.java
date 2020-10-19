package dev.sukanya.gamecourtbooking.factory;

import dev.sukanya.gamecourtbooking.dto.court.BadmintonCourtDTO;
import dev.sukanya.gamecourtbooking.dto.court.CourtDTO;

public class CourtFactory {
    public CourtDTO getCourtDTOType(CourtDTO courtDTO, String gameName){
        if(gameName == null){
            return null;
        }
        if(gameName.equalsIgnoreCase("Badminton")) {

            return new BadmintonCourtDTO(2000);
        }
        else if(gameName.equalsIgnoreCase("Tennis")){
            //return new TennisGameDTO()
        }
        // so on

        //Default
        return courtDTO;
    }
}
