package dev.sukanya.gamecourtbooking.annotations;


import dev.sukanya.gamecourtbooking.validator.PasswordValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "A password should be of minimum 4 letters to maximum 20 letters size " +
            "and should have minimum one uppercase, one lower case, one digit and no empty spaces.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /***
     * With the @Constraint annotation, we tell the class that is going to validate our field, the
     * message() is the error message that is showed in the user interface and the additional code is most
     * boilerplate code to conforms to the Spring standards.
     */
}