package dev.sukanya.gamecourtbooking.model.courts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="location")
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells that DB can give its own Identity -auto incremented- Other types are AUTO (object UUID- default), SEQUENCE (SequenceStyleGenerator - define sequence), TABLE (TableGenerator- underlying sequence stored), Custom (implement IdentityGenerator Interface)
    private int id;

    private String city;

    private String state;

    private String country;

    private String pinCode;

}
