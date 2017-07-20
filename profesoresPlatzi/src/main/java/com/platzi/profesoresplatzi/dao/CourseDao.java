package com.platzi.profesoresplatzi.dao;

import java.util.List;

import com.platzi.profesoresplatzi.model.Course;



public interface CourseDao {

	void saveCourse(Course course);
	
	void deleteCourse(Long idCourse);
	
	void updateCourse(Course teacher);
	
	List<Course>findAllCourses();
	
	Course findCourseById(Long idCourse);
	
	Course findCourseByName(String name);
	
	List<Course> findCourseByIdTeacher(Long idTeacher);
	
	
}
