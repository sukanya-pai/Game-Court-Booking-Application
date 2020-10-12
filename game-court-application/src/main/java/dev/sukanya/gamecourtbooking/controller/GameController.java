package dev.sukanya.gamecourtbooking.controller;

import dev.sukanya.gamecourtbooking.dto.game.GameDTO;
import dev.sukanya.gamecourtbooking.dto.game.GameResponseDTO;
import dev.sukanya.gamecourtbooking.dto.ResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.GameAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Game;
import dev.sukanya.gamecourtbooking.service.interfaces.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j //--> for logging
@RestController //--> we can pass JSON objects too
//if @Controller --> expects we will give name of html page we want to view
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/game/addGame")
    public ResponseDTO<?> addGame(@RequestBody @Valid GameDTO gameDTO, BindingResult result){

        log.info("Received Request for adding new game ",gameDTO.getGameName());
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }
        try{
            Game game = gameService.addGame(gameDTO);
            return new ResponseDTO<GameResponseDTO>(HttpStatus.OK, new GameResponseDTO(game.getId(),game.getGameName(), game.getGameName()+ " added successfully."));
        }catch( GameAlreadyExistsException e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    @PostMapping("/game/addMultipleGames")
    public ResponseDTO<?> addMultipleGames(@RequestBody @Valid List<GameDTO> gameDTOs, BindingResult result){
        log.info("Received Request for adding multiple games");
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }

        try{
            List<String> statuses = gameService.addMultipleGames(gameDTOs);
            return new ResponseDTO<List<String>>(HttpStatus.OK, statuses);
        }catch( Exception e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    public boolean checkForErrors(BindingResult result, List<String> errorMessages){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error:
                    errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}
