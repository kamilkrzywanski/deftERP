package com.defterp.validators.validators;

import com.defterp.validators.annotations.StrictlyPositiveNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

public class StrictlyPositiveNumberValidator implements ConstraintValidator<StrictlyPositiveNumber, Object> {

    @Override
    public void initialize(StrictlyPositiveNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object number, ConstraintValidatorContext context) {
        if (number instanceof Integer) {
            Integer num = (Integer) number;
            return (num > 0);

        } else if (number instanceof Double) {
            Double num = (Double) number;
            return (num > 0d);

        } else if (number instanceof Long) {
            Long num = (Long) number;
            return (num > 0L);

        } else if (number instanceof Float) {
            Float num = (Float) number;
            return (num > 0F);

        } else {
            return false;
        }
    }

}
