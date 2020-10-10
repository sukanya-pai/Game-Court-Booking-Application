package dev.sukanya.gamecourtbooking.annotations;


import dev.sukanya.gamecourtbooking.validator.EmailValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "Please enter a valid email Id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /***
     * With the @Constraint annotation, we tell the class that is going to validate our field, the
     * message() is the error message that is showed in the user interface and the additional code is most
     * boilerplate code to conforms to the Spring standards.
     */
}