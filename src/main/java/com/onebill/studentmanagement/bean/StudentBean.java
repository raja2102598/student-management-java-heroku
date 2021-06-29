package com.onebill.studentmanagement.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
 * Student Bean Class 
 * 
 * @author Raja Jaisankar
 */


@Data//provides all getter,setter methods
@Entity
@Table(name="users")
public class StudentBean  implements Serializable{
	
//	primary key
	@Id
	@Column(name = "Userid")
	private int userid;
//	Student Email
	@Column(name = "Email")
	private String email;
//	Student Marks
	@Column(name = "Marks")
	private double marks;
//	Student name
	@Column(name = "Name")
	private String name;
//	Student Grade(A+,A...F)
	@Column(name = "Grade")
	private String grade;
}
