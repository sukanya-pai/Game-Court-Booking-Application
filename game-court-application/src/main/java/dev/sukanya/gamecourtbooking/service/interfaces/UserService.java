package dev.sukanya.gamecourtbooking.service.interfaces;

import dev.sukanya.gamecourtbooking.dto.user.UserDTO;
import dev.sukanya.gamecourtbooking.exceptions.UserAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.user.User;

public interface UserService {

    public User registerUser(UserDTO userDTO) throws UserAlreadyExistsException;

    public User validateUserOnToken(String token);
}
