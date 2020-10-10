package dev.sukanya.gamecourtbooking.model.courts;

import dev.sukanya.gamecourtbooking.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="timeslot")
@Getter
@Setter
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells that DB can give its own Identity -auto incremented- Other types are AUTO (object UUID- default), SEQUENCE (SequenceStyleGenerator - define sequence), TABLE (TableGenerator- underlying sequence stored), Custom (implement IdentityGenerator Interface)
    private int id;

    private Timestamp startDate;

    private Timestamp endDate;

    private int cost;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "timeSlots")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Court> courts;
}
