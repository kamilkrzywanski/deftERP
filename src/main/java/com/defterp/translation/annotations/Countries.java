package com.defterp.translation.annotations;

import jakarta.inject.Qualifier;

import java.lang.annotation.*;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface Countries {

    Version version();

    public enum Version {
        FIRST,
        SECOND
    }
}
