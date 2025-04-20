package com.defterp.validators.annotations;

import com.defterp.validators.validators.PositiveNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */


@Constraint(validatedBy = PositiveNumberValidator.class)
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface PositiveNumber {


    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

