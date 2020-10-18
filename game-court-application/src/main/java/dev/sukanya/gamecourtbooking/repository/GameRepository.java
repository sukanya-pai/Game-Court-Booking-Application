package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.courts.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {

    Game findGameByGameName(String name);

}
