package com.code.springbootreactjs.service;

import com.code.springbootreactjs.request.EmployeeDto;
import com.code.springbootreactjs.response.ResponseData;
import com.code.springbootreactjs.response.ResponseDto;

public interface EmployeeService {

	ResponseData<?> getAllEmployees();

	ResponseDto createEmployee(EmployeeDto employee);

	ResponseData<?> getEmployeeById(Long id);

	ResponseDto updateEmployee(Long id, EmployeeDto employee);

	ResponseDto deleteEmployee(Long employeeId);

}
