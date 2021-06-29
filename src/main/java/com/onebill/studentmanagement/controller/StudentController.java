package com.onebill.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.studentmanagement.bean.StudentBean;
import com.onebill.studentmanagement.bean.StudentResponse;
import com.onebill.studentmanagement.service.StudentService;

/*
 * Spring Rest Api Controller
 * 
 * It handles all the Requests and forwards it to the services for processing and finally it sends the response.
 * 
 * @author Raja Jaisankar
 */

@RestController
public class StudentController {

//	dependency injection for service
	@Autowired
	StudentService service;


	@GetMapping("/")
	public String homePage(){
		return "hello";
	}

	/*
	 * method for inserting student data
	 * 
	 * @param bean - StudentBean class Object(RequestBody)
	 * 
	 * URL : localhost:8080/StudentManagement/addStudent
	 * 
	 * Body : { "email":"name@gmail.com", "marks":9.1, "name":"name", "grade":"A+" }
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/addStudent", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StudentResponse addStudent(@RequestBody StudentBean bean) {

		StudentResponse response = new StudentResponse(); // Student Response Object
//		 inserting Student data to database
		if (service.addStudent(bean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data inserted");
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDescription("Could not Insert the data ");
		}

		return response; // sending response to client

	}

	/*
	 * method to get particular student
	 * 
	 * @param id(int) - userId(PathVariable)
	 * 
	 * URL : localhost:8080/StudentManagement/getStudent/2
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/getStudent/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public StudentResponse getStudent(@PathVariable(name = "id") int id) {

		StudentBean student = service.getStudent(id); // getting Student data from database
		StudentResponse response = new StudentResponse(); // Student Response Object

		if (student != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data Found For ID " + id);
			response.setStudentBean(student);
		} else {
			response.setStatusCode(404);
			response.setMsg("Failure");
			response.setDescription("Data Not Found For ID " + id);
		}

		return response;// sending response to client
	}

	/*
	 * method to delete particular student
	 * 
	 * @param id(int) - userId(PathVariable)
	 * 
	 * URL : localhost:8080/StudentManagement/removeStudent/2
	 */

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(path = "/removeStudent/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public StudentResponse removeStudent(@PathVariable(name = "id") int id) {

		StudentResponse response = new StudentResponse();// Student Response Object

//		 deleting Student data from database
		if (service.deleteStudent(id)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data deleted for id " + id);

		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDescription("Could not Delete the data for " + id);
		}
		return response;// sending response to client
	}

	/*
	 * method to update particular student email
	 * 
	 * @param bean - StudentBean class Object(RequestBody)
	 * 
	 * URL : localhost:8080/StudentManagement/updateStudentEmail
	 * 
	 * Body : { "userid":1, "email":"name@gmail.com" }
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/updateStudentEmail", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StudentResponse updateStudentEmail(@RequestBody StudentBean bean) {

		StudentResponse response = new StudentResponse();// Student Response Object
		// updating Student email in database
		if (service.updateStudentEmail(bean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data Updated");

		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDescription("Could not Update the data ");
		}
		return response;// sending response to client
	}

	/*
	 * method to get Aggregate Marks and grade
	 * 
	 * @param q1mark(double) - q1mark(PathVariable)
	 * 
	 * @param q2mark(double) - q2mark(PathVariable)
	 * 
	 * @param q3mark(double) - q3mark(PathVariable)
	 * 
	 * @param q4mark(double) - q4mark(PathVariable)
	 * 
	 * @param bean - StudentBean object(RequestBody)
	 * 
	 * URL : localhost:8080/StudentManagement/marks/8.4/9.7/7.8/7.1/
	 * 
	 * Body : { "userid":2, "email":"name@gmail.com", "name":"name" }
	 *   
	 *   
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/marks/{q1mark}/{q2mark}/{q3mark}/{q4mark}/", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StudentResponse studentAggregateMarksGrade(@RequestBody StudentBean bean,
			@PathVariable(name = "q1mark") double q1mark, @PathVariable(name = "q2mark") double q2mark,
			@PathVariable(name = "q3mark") double q3mark, @PathVariable(name = "q4mark") double q4mark) {

		StudentResponse response = new StudentResponse();// Student Response Object
		bean.setMarks(((q1mark + q2mark + q3mark + q4mark) / 4)); // calculating aggregate marks
		double marks = bean.getMarks();

//		Assigning Grade based on aggregated mark 
		if (marks < 4.1) {
			bean.setGrade("F");
		} else if (marks >= 4.1 && marks < 5.1) {
			bean.setGrade("E");
		} else if (marks >= 5.1 && marks < 6.1) {
			bean.setGrade("D");
		} else if (marks >= 6.1 && marks < 7.1) {
			bean.setGrade("C");
		} else if (marks >= 7.1 && marks < 8.1) {
			bean.setGrade("B");
		} else if (marks >= 8.1 && marks < 9.1) {
			bean.setGrade("A");
		} else if (marks >= 9.1 && marks <= 10) {
			bean.setGrade("A+");
		}

		StudentBean studentBean = service.studentAggregateMarksGrade(bean); // Calling Service method to store the
																			// object
		if (studentBean != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Student Aggregate Marks For The Acadamic Year is " + studentBean.getMarks()
					+ ", Student Grade is " + studentBean.getGrade());
			response.setStudentBean(studentBean);
		} else {
			response.setStatusCode(400);
			response.setMsg("Failure");
			response.setDescription("Could not Insert the data ");
		}
		return response;// sending response to client
	}
}
