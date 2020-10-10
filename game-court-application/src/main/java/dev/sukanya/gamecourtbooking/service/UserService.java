package dev.sukanya.gamecourtbooking.service;

import dev.sukanya.gamecourtbooking.dto.UserDTO;
import dev.sukanya.gamecourtbooking.exceptions.UserAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.User;

public interface UserService {

    public User registerUser(UserDTO userDTO) throws UserAlreadyExistsException;

    public User validateUserOnToken(String token);
}
