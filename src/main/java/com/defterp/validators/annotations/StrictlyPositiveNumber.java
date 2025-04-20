package com.defterp.validators.annotations;

import com.defterp.validators.validators.StrictlyPositiveNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */


@Constraint(validatedBy = StrictlyPositiveNumberValidator.class)
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface StrictlyPositiveNumber {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

