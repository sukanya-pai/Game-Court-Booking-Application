package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.game.GameDTO;
import dev.sukanya.gamecourtbooking.exceptions.GameAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.courts.Game;
import dev.sukanya.gamecourtbooking.repository.GameRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.GameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class GameServiceImplTests {
    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;


    private static Game game;

    @BeforeAll
    public static void createDummies(){
        game = new Game();
        game.setId(7);
        game.setGameName("Shuttle");
    }
    @Test
    @DisplayName("Test add Game Success")
    public void testAddGameSuccess() throws Exception {

        doReturn(game).when(gameRepository).save(any());

        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameName("Shuttle");
        Game gameDB = gameService.addGame(gameDTO);
        assertThat(gameDB.getId()).isEqualTo(game.getId());
    }

    @Test
    @DisplayName("Test add Game Failure")
    public void testAddGameFailure() throws Exception {

        doReturn(game).when(gameRepository).findGameByGameName(any());

        GameDTO gameDTO = new GameDTO();
        gameDTO.setGameName("Shuttle");

        String actualMessage="";
        try{
            Game gameDB = gameService.addGame(gameDTO);
        }
        catch(GameAlreadyExistsException gameAlreadyExistsException){
            actualMessage = gameAlreadyExistsException.getMessage();
        }

        assertThat(actualMessage.equals("Game already exists!"));
    }

    @Test
    @DisplayName("Test add Multiple Games")
    public void testAddMultipleGames() throws Exception {

        //mock the method
        doReturn(game).when(gameRepository).save(any());

        List<GameDTO> games = new ArrayList<>();
        GameDTO gameDTO1 = new GameDTO();
        gameDTO1.setGameName("Shuttle");

        GameDTO gameDTO2 = new GameDTO();
        gameDTO2.setGameName("Carrom");
        games.add(gameDTO1);
        games.add(gameDTO2);
        List<String> statuses = gameService.addMultipleGames(games);
        assertThat(statuses.size()).isEqualTo(2);
    }
}
