package dev.sukanya.gamecourtbooking.annotations;

import dev.sukanya.gamecourtbooking.validator.EmailValidator;
import dev.sukanya.gamecourtbooking.validator.PinCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PinCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PinCodeConstraint {
    String message() default "Please enter a valid Pin Code. Pin Code should consist only of digits and length 6.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /***
     * With the @Constraint annotation, we tell the class that is going to validate our field, the
     * message() is the error message that is showed in the user interface and the additional code is most
     * boilerplate code to conforms to the Spring standards.
     */
}
