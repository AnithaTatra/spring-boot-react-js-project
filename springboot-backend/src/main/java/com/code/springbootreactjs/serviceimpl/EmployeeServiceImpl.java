package com.code.springbootreactjs.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.springbootreactjs.entity.Employee;
import com.code.springbootreactjs.exception.ResourceNotFoundException;
import com.code.springbootreactjs.repository.EmployeeRepository;
import com.code.springbootreactjs.request.EmployeeDto;
import com.code.springbootreactjs.response.ResponseData;
import com.code.springbootreactjs.response.ResponseDto;
import com.code.springbootreactjs.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ResponseData<?> getAllEmployees() {
		ResponseData response = new ResponseData();

		try {
			EmployeeDto employee = new EmployeeDto();
			List<Employee> employees = employeeRepository.findAll();
			List<Employee> listEmployees = new ArrayList<>();
			if (employees != null && !employees.isEmpty()) {
				for (Employee emp : employees) {
					employee.setFirstName(emp.getFirstName());
					employee.setLastName(emp.getLastName());
					employee.setEmail(emp.getEmail());
					employee.setSalary(emp.getSalary());
					listEmployees.add(emp);

				}
			} else {

				throw new RuntimeException("Data Not Found");
			}

			response.setData(listEmployees);
			response.setStatus("SUCCESS");

		} catch (ResourceNotFoundException e) {

			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public ResponseDto createEmployee(EmployeeDto employee) {
		ResponseDto employeeDto = new ResponseDto();
		Optional<Employee> existingEmail = employeeRepository.findByEmail(employee.getEmail());
		try {
			if (existingEmail.isPresent()) {
				throw new ResourceNotFoundException(
						"Employee already exists with given Email Id.Please enter a new Email Id : "
								+ employee.getEmail());
			} else {
				Employee employees = new Employee();
				employees.setFirstName(employee.getFirstName());
				employees.setLastName(employee.getLastName());
				employees.setEmail(employee.getEmail());
				employees.setSalary(employee.getSalary());
				employeeRepository.save(employees);
				employeeDto.setMessage("CREATED SUCCESSFULLY");
			}
		} catch (ResourceNotFoundException e) {

			throw new RuntimeException(e);
		}
		return employeeDto;
	}

	@Override
	public ResponseData getEmployeeById(Long id) {
		ResponseData response = new ResponseData();

		try {

			Employee emp = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
			response.setData(emp);
			response.setStatus("SUCCESS");

		} catch (ResourceNotFoundException e) {

			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public ResponseDto updateEmployee(Long id, EmployeeDto employee) {
		ResponseDto response = new ResponseDto();

		try {
			Employee existingEmp = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
			existingEmp.setFirstName(employee.getFirstName());
			existingEmp.setLastName(employee.getLastName());
			existingEmp.setEmail(employee.getEmail());
			existingEmp.setSalary(employee.getSalary());
			employeeRepository.save(existingEmp);
			response.setMessage("UPDATED SUCCESSFULLY");

		} catch (ResourceNotFoundException e) {

			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public ResponseDto deleteEmployee(Long id) {
		ResponseDto response = new ResponseDto();

		try {
			Employee existingEmployee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

			employeeRepository.deleteById(id);
			response.setMessage("DELETED SUCCESSFULLY");

		} catch (ResourceNotFoundException e) {

			throw new RuntimeException(e);
		}

		return response;

	}

}
