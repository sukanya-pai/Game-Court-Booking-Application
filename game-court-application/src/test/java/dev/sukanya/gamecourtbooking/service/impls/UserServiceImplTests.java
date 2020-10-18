package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.user.UserDTO;
import dev.sukanya.gamecourtbooking.exceptions.UserAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.model.user.VerificationToken;
import dev.sukanya.gamecourtbooking.repository.UserRepository;
import dev.sukanya.gamecourtbooking.repository.VerificationTokenRepository;
import dev.sukanya.gamecourtbooking.service.interfaces.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceImplTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VerificationTokenRepository  verificationTokenRepository;

    private static User user;
    private static VerificationToken verificationToken;

    @BeforeAll
    public static void createDummies(){
        user = new User();
        user.setFullName("Sukanya Pai");
        user.setId(1L);
        user.setEmail("sukanyasurendrapai@gmail.com");
        user.setPassword("Pass@123");

        verificationToken = new VerificationToken(user);
    }

    @Test
    @DisplayName("Test Register User Success")
    public void testRegisterUserSuccess() throws Exception {

        doReturn(user).when(userRepository).save(any());

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName("Sukanya Pai");
        userDTO.setEmail("sukanyasurendrapai@gmail.com");
        userDTO.setPassword("Pass@123");
        User userDB = userService.registerUser(userDTO);
        assertThat(userDB.getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("Test Register User Failure")
    public void testRegisterUserFailure() throws Exception {

        doReturn(user).when(userRepository).findByEmail(any());

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName("Sukanya Pai");
        userDTO.setPassword("Pass@123");
        userDTO.setEmail("sukanyasurendrapai@gmail.com");


        String actualMessage="";
        try{
            User userDB = userService.registerUser(userDTO);
        }
        catch(UserAlreadyExistsException userAlreadyExistsException){
            actualMessage = userAlreadyExistsException.getMessage();
        }

        assertThat(actualMessage.equals("User already exists!"));
    }

    @Test
    @DisplayName("Test Validate Token Success")
    public void testValidateTokenSuccess() throws Exception {

        doReturn(verificationToken).when(verificationTokenRepository).findByToken(any());

        User userDB = userService.validateUserOnToken("SomeRandomToken!123");
        assertThat(userDB.getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("Test Validate Token Failure")
    public void testValidateTokenFailure() throws Exception {

        doReturn(null).when(verificationTokenRepository).findByToken(any());

        User userDB = userService.validateUserOnToken("SomeRandomToken!123");
        assertThat(userDB).isNull();
    }

}
