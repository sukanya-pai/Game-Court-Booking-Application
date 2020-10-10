package dev.sukanya.gamecourtbooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission",joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="permission_id",referencedColumnName = "id"))
    private List<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles") // Whenever i fetch roles, please dont fetch users unless i ask for it --> by default fetchType is Lazy
    private List<User> users;
}
