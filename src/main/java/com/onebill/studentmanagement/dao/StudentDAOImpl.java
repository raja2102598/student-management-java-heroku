package com.onebill.studentmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.onebill.studentmanagement.bean.StudentBean;
import com.onebill.studentmanagement.customexceptions.StudentException;

/*
 * Implementation of StudentDAO
 * 
 *  This Class does all the database operations
 * 
 * @author Raja Jaisankar
 */

@Repository
public class StudentDAOImpl implements StudentDAO {

	/*
	 * Insert student data to the database
	 * 
	 * @param bean - StudentBean Object
	 */
	@Override
	public boolean addStudent(StudentBean bean) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("student");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			System.out.println(bean.toString());
			transaction.begin();
			manager.persist(bean); // Insert the data
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * get student data from the database
	 * 
	 * @param id - Userid
	 */
	@Override
	public StudentBean getStudent(int id) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		factory = Persistence.createEntityManagerFactory("student");
		manager = factory.createEntityManager();
		StudentBean bean = manager.find(StudentBean.class, id); // find() returns matching student data for the given
		if (bean != null) {
			return bean;
		} else {
			throw new StudentException("Student Details Not Found");

		}

	}
	
	/*
	 * delete student data from the database
	 * 
	 * @param id - Userid
	 */
	@Override
	public boolean deleteStudent(int id) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("student");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
//			find Data then removing data
			StudentBean bean = manager.find(StudentBean.class, id);
			manager.remove(bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}

	}
	
	/*
	 * update student email for the student
	 * 
	 * @param bean - StudentBean Object
	 */
	@Override
	public boolean updateStudentEmail(StudentBean bean) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			factory = Persistence.createEntityManagerFactory("student");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
//			find Data then update email
			StudentBean oldBean = manager.find(StudentBean.class, bean.getUserid());
			if (bean.getEmail() != null && bean.getEmail() != " ") {
				oldBean.setEmail(bean.getEmail());
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
	}
	
	/*
	 * Get Aggregate marks,grade and store data to database 
	 * 
	 * @param bean - StudentBean Object
	 */
	@Override
	public StudentBean studentAggregateMarksGrade(StudentBean bean) {

		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		factory = Persistence.createEntityManagerFactory("student");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		System.out.println(bean.toString());
		try {
			transaction.begin();
			manager.persist(bean);
			StudentBean studentBean = manager.find(StudentBean.class, bean.getUserid());
			transaction.commit();
			return studentBean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StudentException("Something Went Wrong!!!!!!!!!!!");
		}
	}

}
