package com.mantu.electronic.store.ElectronicStore.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class BadApiRequestException extends RuntimeException{
    private Logger logger= LoggerFactory.getLogger(BadApiRequestException.class);
    public BadApiRequestException(String message){
        super(message);
    }
    public BadApiRequestException(){
        super("Bad Request!!");
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequestException ex)

    {
        logger.info("Bad Api request!!");
        ApiResponseMessage response =ApiResponseMessage.builder().message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity<ApiResponseMessage>(response,HttpStatus.BAD_REQUEST);

    }

}
