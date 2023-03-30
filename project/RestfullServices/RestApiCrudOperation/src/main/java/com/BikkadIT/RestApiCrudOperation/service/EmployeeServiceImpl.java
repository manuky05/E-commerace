package com.BikkadIT.RestApiCrudOperation.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.RestApiCrudOperation.model.Employee;
import com.BikkadIT.RestApiCrudOperation.repository.EmployreeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeServicreI {
	
	@Autowired
	private EmployreeRepository employeeRepository;

	@Override
	public boolean saveEmployee(Employee emp) {
		Employee employee=employeeRepository.save(emp);
		if(employee!=null) {
			return true;
			
		}else {
			
			return false;	
			
		}	
	}

	@Override
	public Boolean saveAllEmployee(List<Employee> list) {
		List<Employee> saveAll= employeeRepository.saveAll(list);
		if(saveAll !=null) {
			
			return true;
			
		}else {
			
		return null;
	}
	}
	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> findAll = employeeRepository.findAll();
		return findAll;
	}

	@Override
	public Employee getByEmployeeIdQueryParam(int empId) {
		Employee findById=employeeRepository.findById(empId).get();
		return findById;
	}

	@Override
	public Employee getByEmployeeIdPathParam(int empId) {
		Employee findById =employeeRepository.findById(empId).get();
		return findById;
	}

	@Override
	public Employee getByEmployeeRequestBody(Employee emp) {
		int empId=emp.getEmpId();
		Employee findById =employeeRepository.findById(empId).get();
		return findById;
	}

	@Override
	public boolean updateEmploye(Employee emp) {
		Employee employee=employeeRepository.save(emp);
			if(employee!=null) {
				return true;
			
				
			}else{
		
		return false;
			}
	}

	@Override
	public boolean updateAllEmployee(List<Employee> emp) {
		
		return false;
	}

	@Override
	public boolean deleteById(int empId) {
		boolean ExistsById=employeeRepository.existsById(empId);
		if(ExistsById) {
			employeeRepository.deleteById(empId);
		
			return true;
	}else {
		return false;
	}
	}
	@Override
	public boolean deleteAllEmployee() {
		List<Employee>findAll=employeeRepository.findAll();
		
		if(findAll !=null) {
			employeeRepository.deleteAll();
		return true;
		}else {
		return false;
	}
	}

}
