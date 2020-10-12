package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.game.GameDTO;
import dev.sukanya.gamecourtbooking.exceptions.GameAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Game;
import dev.sukanya.gamecourtbooking.repository.GameRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service    //Service says Spring Boot that this is a bean and make sure this bean is created
@Transactional  //for all methods of service, give transactional properties same as database
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game addGame(GameDTO gameDTO) throws GameAlreadyExistsException {
        Game gameDB = gameRepository.findGameByGameName(gameDTO.getGameName());
        if(gameDB!=null && gameDB.getGameName().equalsIgnoreCase(gameDTO.getGameName())){
            //Game already exists, throw exception
            throw new GameAlreadyExistsException("Game already exists!");
        }

        Game gameForDB = new Game();
        gameForDB.setGameName(gameDTO.getGameName());

        Game savedGame = gameRepository.save(gameForDB); //This savedGame object will have ID saved in DB

        return savedGame;
    }

    @Override
    public List<String> addMultipleGames(List<GameDTO> gameDTOs) {
        List<String> statuses = new ArrayList<>();
        for(GameDTO game : gameDTOs){
            try{
                Game gameFromDB = addGame(game);
                statuses.add(gameFromDB.getGameName() + " successfully added.");
            }
            catch(GameAlreadyExistsException gameAlreadyExistsException){
                statuses.add(game.getGameName() + " already exists.");
            }
        }
        return statuses;
    }

}
