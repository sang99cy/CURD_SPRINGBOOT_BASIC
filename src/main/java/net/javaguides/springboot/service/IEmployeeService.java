package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Employee;


public interface IEmployeeService {
	List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
	Page<Employee> listAll(int pageNumber);
}
