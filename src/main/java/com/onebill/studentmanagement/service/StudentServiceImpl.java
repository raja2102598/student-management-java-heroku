package com.onebill.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebill.studentmanagement.bean.StudentBean;
import com.onebill.studentmanagement.dao.StudentDAO;

/*
 * Implementation of Service Interface
 */
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDAO dao;

	@Override
	public boolean addStudent(StudentBean bean) {
		return dao.addStudent(bean);
	}

	@Override
	public StudentBean getStudent(int id) {
		return dao.getStudent(id);
	}

	@Override
	public boolean deleteStudent(int id) {
		return dao.deleteStudent(id);
	}

	@Override
	public boolean updateStudentEmail(StudentBean bean) {
		return dao.updateStudentEmail(bean);
	}

	@Override
	public StudentBean studentAggregateMarksGrade(StudentBean bean) {
		return dao.studentAggregateMarksGrade(bean);
	}

}
