package dev.sukanya.gamecourtbooking.validator;


import dev.sukanya.gamecourtbooking.annotations.PasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext cxt) {

        String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=_])"
            + "(?=\\S+$).{4,20}$";
        boolean isValid = password != null && password.matches(regex);
        return isValid ;
    }

}