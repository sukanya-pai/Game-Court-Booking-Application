package dev.sukanya.gamecourtbooking.validator;

import dev.sukanya.gamecourtbooking.annotations.PinCodeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PinCodeValidator implements
        ConstraintValidator<PinCodeConstraint, String> {

    @Override
    public void initialize(PinCodeConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String pinCode,
                           ConstraintValidatorContext cxt) {
        boolean isValid = pinCode != null && pinCode.matches("^[0-9]+") && pinCode.length()==6;
        return isValid ;
    }
}
