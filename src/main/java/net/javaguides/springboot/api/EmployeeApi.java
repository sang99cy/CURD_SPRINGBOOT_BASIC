package net.javaguides.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.ResponseObject;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApi {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping(value = "/save")
     public ResponseObject saveEmployee(@RequestBody @Valid Employee employee, BindingResult result) {
		ResponseObject ro = new ResponseObject();
 		if (result.hasErrors()) {
 			Map<String, String> errors = result.getFieldErrors().stream()
 					.collect(Collectors.toMap(FieldError :: getField, FieldError::getDefaultMessage));
 			ro.setErrorMessages(errors);
 			List<String> keys = new ArrayList<String>(errors.keySet());
 			for(String key : keys) {
 				System.out.println(key + ":" + errors.get(key));
 			}
 			ro.setStatus("fail");
			errors = null;
 		}
 		else {
 			employee.setPassWord(passwordEncoder.encode(employee.getPassWord()));
 			employeeRepository.save(employee);
 			ro.setStatus("cusccess");
 			ro.setData(employee);
		}
		return ro;
 		
 	}
	@GetMapping("/list-employees")
	 public List<Employee>  ListEmployee() {
		
		return employeeRepository.findAll();
 		
 	}
}
