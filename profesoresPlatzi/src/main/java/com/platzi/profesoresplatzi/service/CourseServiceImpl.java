package com.platzi.profesoresplatzi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.profesoresplatzi.dao.CourseDao;
import com.platzi.profesoresplatzi.model.Course;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao _courseDao;
	
	@Override
	public void saveCourse(Course course) {
		// TODO Auto-generated method stub
		_courseDao.saveCourse(course);
	}

	@Override
	public void deleteCourse(Long idCourse) {
		// TODO Auto-generated method stub
		_courseDao.deleteCourse(idCourse);
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		_courseDao.updateCourse(course);
		
	}

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return _courseDao.findAllCourses();
	}

	@Override
	public Course findCourseById(Long idCourse) {
		// TODO Auto-generated method stub
		return _courseDao.findCourseById(idCourse);
	}

	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
		return _courseDao.findCourseByName(name);
	}

	@Override
	public List<Course> findCourseByIdTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return _courseDao.findCourseByIdTeacher(idTeacher);
	}

}
