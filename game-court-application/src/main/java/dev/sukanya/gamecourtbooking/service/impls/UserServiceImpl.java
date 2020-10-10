package dev.sukanya.gamecourtbooking.service.impls;

import dev.sukanya.gamecourtbooking.dto.UserDTO;
import dev.sukanya.gamecourtbooking.event.SuccessfulRegistrationEvent;
import dev.sukanya.gamecourtbooking.exceptions.UserAlreadyExistsException;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.model.user.VerificationToken;
import dev.sukanya.gamecourtbooking.repository.UserRepository;
import dev.sukanya.gamecourtbooking.repository.VerificationTokenRepository;
import dev.sukanya.gamecourtbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service    //Service says Spring Boot that this is a bean and make sure this bean is created
@Transactional  //for all methods of service, give transactional properties same as database
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(userDTO.getEmail())!=null){
            //User already exists, throw exception
            throw new UserAlreadyExistsException("User already exists!");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setActive(false);

        // Spring allows us to send password Encoder --> SpringSecurity Comes into picture. --> defined in SecurityConfig
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //When a person logs in, we will again encrypt the password with same algorithm
        //We compare the encrypted password with what we have
        //There is no need of decrypting password.
        //Companies generally dont store the password for security concerns

        User savedUser = userRepository.save(user); //This savedUser object will have ID saved in DB

        //inbuilt event publisher provided by Spring boot as we are not doing anything special
        applicationEventPublisher.publishEvent(new SuccessfulRegistrationEvent(savedUser)); //events are messages that are produced by someone and consumed by someone
        return savedUser;
    }

    @Override
    public User validateUserOnToken(String token){
        //TODO: Check token repo if there is that token
        //TODO: if it exists check if it belongs to the user
        //TODO: check if it is not expired yet

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken==null) return null;

        if(verificationToken.getExpiryTime().getTime() - new Date().getTime()>0){
            //token not yet expired
            //mark user as active
            User verifiedUser = verificationToken.getUser();
            verifiedUser.setActive(true);
            userRepository.save(verifiedUser);

            //Also remove verification token
            verificationTokenRepository.delete(verificationToken);
            return verifiedUser;
        }else{
            return null;
        }

        //if not expired, return verified user
        //else return null;

    }
}
