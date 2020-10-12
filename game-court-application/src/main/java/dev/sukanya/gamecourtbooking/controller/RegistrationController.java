package dev.sukanya.gamecourtbooking.controller;

import dev.sukanya.gamecourtbooking.dto.ResponseDTO;
import dev.sukanya.gamecourtbooking.dto.user.UserDTO;
import dev.sukanya.gamecourtbooking.dto.user.UserResponseDTO;
import dev.sukanya.gamecourtbooking.exceptions.UserAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j //--> for logging
@RestController //--> we can pass JSON objects too
//if @Controller --> expects we will give name of html page we want to view
public class RegistrationController {
    @Autowired  //Autowired --> For Dependency Injection in AOP programming
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseDTO<?> registerUser(@RequestBody @Valid UserDTO userDTO, BindingResult result){
        log.info("Received Request for registration",userDTO.getEmail());
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            List<String> errorMessages = new ArrayList<String>();
            for (ObjectError error:
                 errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }
        try{
            User user = userService.registerUser(userDTO);
            return new ResponseDTO<UserResponseDTO>(HttpStatus.OK, new UserResponseDTO(user.getId(),user.getEmail(),user.getFullName(),user.isActive()));
        }catch(UserAlreadyExistsException e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/user/confirm")
    public ResponseDTO<UserResponseDTO> validateUser(@RequestParam String token){
        User user = userService.validateUserOnToken(token);

        return new ResponseDTO<UserResponseDTO>(HttpStatus.OK,new UserResponseDTO(user.getId(),user.getEmail(),user.getFullName(),user.isActive()));
    }
}
