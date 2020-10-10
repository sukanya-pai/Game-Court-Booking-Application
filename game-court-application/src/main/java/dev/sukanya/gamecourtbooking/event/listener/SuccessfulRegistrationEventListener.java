package dev.sukanya.gamecourtbooking.event.listener;

import dev.sukanya.gamecourtbooking.event.SuccessfulRegistrationEvent;
import dev.sukanya.gamecourtbooking.model.user.User;
import dev.sukanya.gamecourtbooking.model.user.VerificationToken;
import dev.sukanya.gamecourtbooking.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventListener implements ApplicationListener<SuccessfulRegistrationEvent> {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {
        //what should i do when i get this event
        User registeredUser = successfulRegistrationEvent.getRegisteredUser();

        //TODO: send email to verify user

        VerificationToken token = new VerificationToken(registeredUser);

        verificationTokenRepository.save(token);
    }
}
