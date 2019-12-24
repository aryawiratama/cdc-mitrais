package com.cdc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NumericValidator.class)
public @interface IsNumeric {
    String message() default "Field must be a number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
