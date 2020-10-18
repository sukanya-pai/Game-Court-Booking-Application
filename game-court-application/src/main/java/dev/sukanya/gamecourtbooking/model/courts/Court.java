package dev.sukanya.gamecourtbooking.model.courts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="court")
@Getter
@Setter
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courtName;

    @ManyToOne(fetch = FetchType.EAGER) // This will  load roles along with users --> by default fetchType is Lazy for Many to Many
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "court_timeslot",
            joinColumns = @JoinColumn(name="court_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="timeslot_id",referencedColumnName = "id"))
    private List<TimeSlot> timeSlots;

    private long specialCourtChargesPerHour;
 }
