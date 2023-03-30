package com.BikkadIT.RestApiCrudOperation.controller;


import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.RestApiCrudOperation.model.Employee;
import com.BikkadIT.RestApiCrudOperation.service.EmployeeServiceImpl;
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping(value = "/saveEmployee", consumes = "application/json")
	public ResponseEntity<String>  saveEmployee(@RequestBody Employee emp){
		
		 boolean saveEmployee =employeeServiceImpl.saveEmployee(emp);
		 if(saveEmployee) {
		return new ResponseEntity<String> ("saveEmployee Succesfully",HttpStatus.CREATED);
		 }else {
		return new ResponseEntity<String>("saveEmployee not Succesfully",HttpStatus.BAD_REQUEST); 	
	 }
	}
      @GetMapping(value="/getAllEmployee", produces  ="Application/json")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee>list =employeeServiceImpl.getAllEmployee();
		
		return new ResponseEntity<List<Employee>> (list, HttpStatus.OK);
	}
	


   @PostMapping(value="/saveAllEmployee", consumes="Application/json")
   public ResponseEntity<String>saveAllEmployee(@RequestBody List<Employee>emp){
	   boolean saveEmployee=employeeServiceImpl.saveAllEmployee(emp);
	   if(saveEmployee) {
			return new ResponseEntity<String> ("saveEmployee Succesfully",HttpStatus.CREATED);
			 }else {
			return new ResponseEntity<String>("saveEmployee not Succesfully",HttpStatus.BAD_REQUEST); 	
			 }
		 }		 
			@PostMapping (value="/Employee",consumes="Application/json")
		public ResponseEntity<Employee>	getByEmpIdQueryParamEntity(@RequestParam int empId){
			Employee employee =employeeServiceImpl.getByEmployeeIdQueryParam(empId);
			
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
			
		}
			
			@PostMapping (value="/getByEmpIdPathParam/{empId}", produces="Application/json")
		 public ResponseEntity<Employee>getByEmpIdPathParamEntity(@PathVariable int empId){
			 Employee employee =employeeServiceImpl.getByEmployeeIdPathParam(empId);
			 
				return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		 }
			@PostMapping(value="/getEmployeeRequestBody",consumes="Application/json" ,produces="Application/json") 
		 public ResponseEntity<Employee>getEmployeeRequestBody(@RequestBody Employee emp){
			 Employee employeeByRequestBody =employeeServiceImpl.getByEmployeeRequestBody(emp);
			 
				return new ResponseEntity<Employee>(employeeByRequestBody ,HttpStatus.OK);
			 
		 }
			@PutMapping(value = "/updateEmployee", consumes = "application/json") 
		public ResponseEntity<String> updateEmployee (@RequestBody  Employee emp){
			boolean saveEmployee=employeeServiceImpl.updateEmploye(emp);
			if(saveEmployee) {
				return new ResponseEntity<String>("Employee Update Sucessfully", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Employee not Update Sucessfully", HttpStatus.BAD_REQUEST);
			}
		}
		
		@DeleteMapping(value = "/deleteEmployeeByID/{empId}")
		public ResponseEntity<String> deleteEmployeeByID(@PathVariable int empId) {
			boolean deleteById = employeeServiceImpl.deleteById(empId);

			if(deleteById) {
				String msg="Employee Deleted Successfully";
				return new ResponseEntity<String>(msg, HttpStatus.OK);
			}else {
			String msg="Employee Not deleted Successfully";
			return new ResponseEntity<String>(msg, HttpStatus.OK);

		}
		}

		
		@DeleteMapping("/deleteAllEmployee")
		public ResponseEntity<String> deleteAllEmployee(){
			
			boolean deleteAllEmployee = employeeServiceImpl.deleteAllEmployee();
			
			
			if(deleteAllEmployee) {
				String msg="ALl Employee Deleted Successfully";
				return new ResponseEntity<String>(msg, HttpStatus.OK);
			}else {
			String msg=" All Employee Not deleted Successfully";
			return new ResponseEntity<String>(msg, HttpStatus.OK);

		}
			
		}
		
		

			 
			 
	
			 	
}