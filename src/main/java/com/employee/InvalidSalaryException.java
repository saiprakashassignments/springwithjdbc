package com.employee;


public class InvalidSalaryException extends RuntimeException{
	

	InvalidSalaryException(String message){
		System.out.println(message);
	}

}
