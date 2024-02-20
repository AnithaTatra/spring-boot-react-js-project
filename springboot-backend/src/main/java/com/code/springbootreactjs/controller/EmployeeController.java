package com.code.springbootreactjs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.springbootreactjs.request.EmployeeDto;
import com.code.springbootreactjs.response.ResponseData;
import com.code.springbootreactjs.response.ResponseDto;
import com.code.springbootreactjs.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("*")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/welcome")
	public String helloWorld() {
		logger.info("************ STARTING SAMPLE TEST API **************");
		return "Hello World!";
	}

	// build create employee REST API
	@PostMapping
	public ResponseDto createEmployee(@RequestBody EmployeeDto employee) {
		ResponseDto employeeDto = null;
		try {
			logger.info("************ STARTING EMPLOYEE CREATE API **************");
			employeeDto = employeeService.createEmployee(employee);
			logger.info("************ ENDED EMPLOYEE CREATE API **************");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return employeeDto;

	}

	// build get employee REST API
	@GetMapping
	public ResponseData getEmployees() {
		ResponseData response = null;
		try {
			logger.info("************ STARTING EMPLOYEE GET API **************");
			response = employeeService.getAllEmployees();
			logger.info("************ ENDED EMPLOYEE GET API **************");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;

	}

	// build get employee by id REST API
	@GetMapping("{id}")
	public ResponseData getEmployeeById(@PathVariable("id") Long id) {
		ResponseData response = null;
		try {
			logger.info("************ STARTING  GET SINGLE EMPLOYEE API **************");
			response = employeeService.getEmployeeById(id);
			logger.info("************ ENDED  GET SINGLE EMPLOYEE API **************");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	// build update employee REST API
	@PutMapping("{id}")
	public ResponseDto updateEmployeeById(@PathVariable("id") Long id, @RequestBody EmployeeDto employee) {
		ResponseDto response = null;
		try {
			logger.info("************ STARTING UPDATE EMPLOYEE API **************");
			response = employeeService.updateEmployee(id, employee);
			logger.info("************ ENDED UPDATE EMPLOYEE API **************");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	// build delete employee REST API
	@DeleteMapping("{id}")
	public ResponseDto deleteEmployeeById(@PathVariable("id") Long id) {
		ResponseDto response = null;
		try {
			logger.info("************ STARTING DELETE EMPLOYEE API **************");
			response = employeeService.deleteEmployee(id);
			logger.info("************ ENDED DELETE EMPLOYEEAPI **************");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

}
