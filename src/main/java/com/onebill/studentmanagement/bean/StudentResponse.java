package com.onebill.studentmanagement.bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.onebill.studentmanagement.bean.StudentBean;

import lombok.Data;


/*
 *  Class Which defines Response Message format to client
 *  
 *  
 *  @author Raja Jaisankar
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "statusCode", "msg", "description" })
public class StudentResponse {
	
//	Status code for the response
	private int statusCode;
	
//	Either Success/Failure
	private String msg;

//	description of the response
	private String description;
	
//	student object
	private StudentBean studentBean;
}
