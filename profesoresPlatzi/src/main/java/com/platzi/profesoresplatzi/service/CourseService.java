package com.platzi.profesoresplatzi.service;

import java.util.List;

import com.platzi.profesoresplatzi.model.Course;

public interface CourseService {

void saveCourse(Course course);
	
	void deleteCourse(Long idCourse);
	
	void updateCourse(Course teacher);
	
	List<Course>findAllCourses();
	
	Course findCourseById(Long idCourse);
	
	Course findCourseByName(String name);
	
	List<Course> findCourseByIdTeacher(Long idTeacher);
	
}
