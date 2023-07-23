package com.mantu.electronic.store.ElectronicStore.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Super;

@Setter
@Getter
@Builder
public class ResourceNotFoundException extends RuntimeException {
    //    String resourceName;
//    String fieldName;
//    String fieldValue;
//
//    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
//        super(String.format("%s  not found with %s:%s", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//
//
//    }
    public ResourceNotFoundException() {
        super("Resource Not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);

    }

}