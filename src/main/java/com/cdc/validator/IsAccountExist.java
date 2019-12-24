package com.cdc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountExistValidator.class)
public @interface IsAccountExist {
    String message() default "Account Not Exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
