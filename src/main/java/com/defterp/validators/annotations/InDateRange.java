package com.defterp.validators.annotations;

import com.defterp.validators.validators.InDateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Documented
@Constraint(validatedBy = InDateRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InDateRange {

    String message() default "{InDateRange}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String min() default "1900";

    String max() default "2100";
}
