package dev.sukanya.gamecourtbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.SpringSecurityCoreVersion;

//if we dont exclude, then Spring will inject spring security
// and do the authentication and authorization internally
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class GameCourtBookingApp {

    public static void main(String[] args) {
        SpringApplication.run(GameCourtBookingApp.class, args);
    }

}
