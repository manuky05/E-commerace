package com.mantu.electronic.store.ElectronicStore.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {

    private Logger logger=Logger.global.getLogger(String.valueOf(ImageNameValidator.class));
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("Message  from isValid:{}");
        //logic
        if(value.isBlank()){

        }else{
            return true;
        }
        return false;
    }
}
