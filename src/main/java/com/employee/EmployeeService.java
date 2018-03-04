package com.employee;


public interface EmployeeService {
	void addEmployee(Employee e);

	void deleteEmployee(int e) throws EmployeeNotFoundException;

	void updateEmployee(Employee e) throws EmployeeNotFoundException;

	void displayEmployee(Employee e);

	void displayAllEmployee();

	void displayEmployeeHra(Employee e);

	void displayEmployeeGrossSalary(Employee e);

	Employee getEmployeeById(int empId) throws EmployeeNotFoundException;

}
