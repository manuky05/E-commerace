package com.Mantu.UserManagement.service.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptiomHandler {
	
	private static final String Error = null;
	private static final Consumer<? super ObjectError> error = null;
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotfoundExceptionHandler(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
//	@ResponseStatus(BAD_REQUEST)
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
//        return processFieldErrors(fieldErrors);
//    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgNotValiedException(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldName=((FieldError)error).getField();
			String message =((DefaultMessageSourceResolvable) error).getDefaultMessage();
		
			resp.put(fieldName, message);
			});
			
			return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
		}

	

}
