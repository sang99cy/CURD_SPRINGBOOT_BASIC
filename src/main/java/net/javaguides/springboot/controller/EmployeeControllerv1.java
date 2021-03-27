package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.IEmployeeService;

@Controller
public class EmployeeControllerv1 {
	@Value("${spring.application.name}")
	String appName;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	IEmployeeService iemplyeeService;
	@Autowired
	PasswordEncoder passwordEncoder;

//	@GetMapping("/")
//	public String ListEmployees(Model model) {
//		return viewPage(model, 1,"");
//	}
	@RequestMapping("/page/{pageNumber}")
	public String viewPage(Model model,@PathVariable(name = "pageNumber") int currentPage,@RequestParam(value = "firstName",required = false) String firstName) {
		Page<Employee> page = iemplyeeService.listAll(currentPage);
		if(firstName != null) {
			List<Employee> searchItem = employeeRepository.findByFirstNameLike(firstName);
			model.addAttribute("employees", searchItem);
		
		}else {
			List<Employee> list = page.getContent();
			model.addAttribute("employees", list);
		}
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees";
	}	
	
//	@GetMapping("/showNewEmployeeForm")
//	public String showNewEmployeeForm(Model model) {
//		// create model attribute to bind form data
//		Employee employee = new Employee();
//		model.addAttribute("employee", employee);
////		return "add-employee";
//		return "employees";
//	}

	@RequestMapping(path = "/saveEmployee", method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("employee", employee);
			/* return "redirect:/page/1"; */
			return viewPage(model, 1, "");
		}else {
			employee.setPassWord(passwordEncoder.encode(employee.getPassWord()));
			System.out.print(employee.getPassWord());
			employeeRepository.save(employee);
		}	
		/*
		 * List<Employee> list = new ArrayList<Employee>(); list =
		 * employeeRepository.findAll(); model.addAttribute("employees", list);
		 */
		return "redirect:/page/1";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") long id, Model model) {
		employeeRepository.deleteById(id);
//		List<Employee> list = new ArrayList<Employee>();
//		list = employeeRepository.findAll();
//		model.addAttribute("employees", list);
		return "redirect:/page/1";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showformUpdate(@PathVariable("id") long id, Model model) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		model.addAttribute("employee", employee);
		return "edit-employee";
	}
	

}
