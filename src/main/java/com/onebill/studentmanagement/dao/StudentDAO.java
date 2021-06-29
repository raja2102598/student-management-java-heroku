package com.onebill.studentmanagement.dao;

import com.onebill.studentmanagement.bean.StudentBean;

/*
 * Interface for database operations
 * 
 * @author Raja Jaisankar
 */

public interface StudentDAO {

	public boolean addStudent(StudentBean bean);

	public StudentBean getStudent(int id);

	public boolean deleteStudent(int id);

	public boolean updateStudentEmail(StudentBean bean);


	public StudentBean studentAggregateMarksGrade(StudentBean bean);

}
