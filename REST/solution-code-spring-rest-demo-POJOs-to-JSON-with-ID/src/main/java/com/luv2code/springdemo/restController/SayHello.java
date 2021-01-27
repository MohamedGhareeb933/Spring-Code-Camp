package com.luv2code.springdemo.restController;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.student.Student;

@RestController
@RequestMapping("/api")
public class SayHello {
	
	private List<Student> studentList;
	
	// @postConstruct is loaded only once the given bean is constructed
	@PostConstruct
	public void loadStudentList() {
		
		studentList = new ArrayList<>();
		
		studentList.add(new Student("mohamed", "ahmed"));
		studentList.add(new Student("malak", "saeed"));
		studentList.add(new Student("mostafa", "ahmed"));
	}
	
	@GetMapping("/student/index={studentId}")
	public Student getStudentById(@PathVariable int studentId) {	
		return studentList.get(studentId);
	}
	
	@GetMapping("/student")
	public List<Student> getStudentById() {	
		return studentList;
	}

}
