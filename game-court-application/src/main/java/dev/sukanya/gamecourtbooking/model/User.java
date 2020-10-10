package dev.sukanya.gamecourtbooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells that DB can give its own Identity
    private Long id;

    private String fullName;

    private String password;

    private String email;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER) // This will  load roles along with users --> by default fetchType is Lazy for Many to Many
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private List<Role> roles;
}
