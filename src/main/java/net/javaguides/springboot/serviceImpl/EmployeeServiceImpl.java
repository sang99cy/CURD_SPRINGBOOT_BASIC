package net.javaguides.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Employee> pageResult=employeeRepository.findAll(paging);
		 if(pageResult.hasContent()) {
	            return pageResult.getContent();
	        }
		 else {
			new  ArrayList<Employee>();
		}
		return null;
	}

	@Override
	public Page<Employee> listAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		return employeeRepository.findAll(pageable);
	}

}
