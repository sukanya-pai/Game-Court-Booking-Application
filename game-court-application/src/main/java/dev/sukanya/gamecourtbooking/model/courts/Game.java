package dev.sukanya.gamecourtbooking.model.courts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="game")
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells that DB can give its own Identity -auto incremented- Other types are AUTO (object UUID- default), SEQUENCE (SequenceStyleGenerator - define sequence), TABLE (TableGenerator- underlying sequence stored), Custom (implement IdentityGenerator Interface)
    private int id;

    private String gameName;

}
