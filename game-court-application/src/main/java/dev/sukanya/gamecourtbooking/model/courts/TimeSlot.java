package dev.sukanya.gamecourtbooking.model.courts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="timeslot")
@Getter
@Setter
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells that DB can give its own Identity -auto incremented- Other types are AUTO (object UUID- default), SEQUENCE (SequenceStyleGenerator - define sequence), TABLE (TableGenerator- underlying sequence stored), Custom (implement IdentityGenerator Interface)
    private int id;

    private Time startDate;

    private Time endDate;

}
