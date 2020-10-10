package dev.sukanya.gamecourtbooking.dto;

import dev.sukanya.gamecourtbooking.annotations.EmailConstraint;
import dev.sukanya.gamecourtbooking.annotations.PasswordConstraint;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//DTO-> Data Transfer Object --> To need for our usage (kind of model that we used before)
@Getter
@Setter
public class UserDTO {

    @EmailConstraint
    private String email;

    @NotEmpty
    @Size(min = 5)
    private String fullName;

    @PasswordConstraint
    private String password;
}

/***
 * Behind the screens, Spring will receive network object and then create UserDto Object
 * as userDto = new UserDto()
 * userDto.setEmail(email); so on
 *
 * Hence to make spring work on UserDto, we should atleast need Getter Setter or
 * a paramterised constructor to set values to the userDto object
 *
 * Lot of times, when request comes, we would need to validate the requests before it actually hits the controller
 * Hence We can provide such validations in the UserDto class itself. If those validations are not satisfied, then the object
 * is rejected as input.
 *
 * @NoteEmpty and @Size etc are inbuilt validators
 * We can also build Custom Validators on email and password which will ensure that password follows certain restrictions
 * and email is valid
 *
 * ***/