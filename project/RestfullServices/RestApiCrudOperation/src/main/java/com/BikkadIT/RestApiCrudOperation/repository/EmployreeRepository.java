package com.BikkadIT.RestApiCrudOperation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BikkadIT.RestApiCrudOperation.model.Employee;
@Repository
public interface EmployreeRepository extends JpaRepository<Employee, Integer> {

}
