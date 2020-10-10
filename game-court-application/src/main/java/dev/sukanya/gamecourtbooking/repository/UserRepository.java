package dev.sukanya.gamecourtbooking.repository;

import dev.sukanya.gamecourtbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    List<User> findAllByEmail(String email);

    //Behind the screens, Spring Hibernate provides all the features associated with a field as methods and it handles that
}
