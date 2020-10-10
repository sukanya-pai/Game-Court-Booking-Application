package dev.sukanya.gamecourtbooking.validator;


import dev.sukanya.gamecourtbooking.annotations.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {

    @Override
    public void initialize(EmailConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {
        boolean isValid = email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        return isValid ;
    }

}