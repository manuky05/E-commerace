package com.BikkadIT.QueryParamAndPathParam.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddtionController {
	@GetMapping("/printValue/{i}")
	public ResponseEntity<Integer> printValue (@PathVariable Integer i){
		Integer i1=i;
		return new ResponseEntity<Integer>(i1, HttpStatus.OK);
		
	}
	@GetMapping("/add/{a}/{b}")
	public ResponseEntity<Integer> add(@PathVariable int a, @PathVariable int b){
		Integer c=a+b;
		return new ResponseEntity<Integer>(c, HttpStatus.OK);
	}

}
