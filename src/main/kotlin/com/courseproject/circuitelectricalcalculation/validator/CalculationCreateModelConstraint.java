package com.courseproject.circuitelectricalcalculation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CalculationCreateModelValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CalculationCreateModelConstraint {
    String message() default "Invalid calculation create model";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}