package com.defterp.validators.validators;

import com.defterp.validators.annotations.InDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

public class InDateRangeValidator implements ConstraintValidator<InDateRange, Date> {

    private final Date maxDate = new Date(2101, 1, 1, 0, 0);
    private final Date minDate = new Date(1899, 12, 31, 0, 0);

    @Override
    public void initialize(InDateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return value == null || (value.after(minDate) && value.before(maxDate));
    }
}
