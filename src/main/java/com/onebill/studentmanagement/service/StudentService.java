package com.onebill.studentmanagement.service;

import com.onebill.studentmanagement.bean.StudentBean;

/*
 * Interface for services which will be used by Controller 
 * 
 * @author Raja Jaisankar
 */
public interface StudentService {

	public boolean addStudent(StudentBean bean);

	public StudentBean getStudent(int id);

	public boolean deleteStudent(int id);

	public boolean updateStudentEmail(StudentBean bean);

	public StudentBean studentAggregateMarksGrade(StudentBean bean);
}
