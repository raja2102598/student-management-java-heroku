package com.onebill.studentmanagement.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onebill.studentmanagement.bean.StudentResponse;
import com.onebill.studentmanagement.customexceptions.StudentException;

/*
 * Spring Custom Exception Handler class 
 * 
 * @author Raja Jaisankar
 */

@RestControllerAdvice
public class StudentControllerAdvice {

//	implementing custom exceptions
	@ExceptionHandler(StudentException.class)
	public StudentResponse handleStudentException(StudentException exception) {
		StudentResponse response = new StudentResponse();
		response.setStatusCode(500);
		response.setMsg("failure");
		response.setDescription(exception.getMessage());//message from throw new Exception(message)
		return response;// sending response to client
	}
}
