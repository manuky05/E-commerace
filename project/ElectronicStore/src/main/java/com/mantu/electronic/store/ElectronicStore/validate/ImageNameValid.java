package com.mantu.electronic.store.ElectronicStore.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {
    //error message
 String message() default"Invalid Image Name!!" ;
 //reparent group of constarant
 Class<?>[]groups()default{};

 Class<? extends Payload>[]payload()default{};
}
