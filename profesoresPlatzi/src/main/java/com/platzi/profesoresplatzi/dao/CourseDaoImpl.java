package com.platzi.profesoresplatzi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.platzi.profesoresplatzi.model.Course;


@Repository
@Transactional
public class CourseDaoImpl extends AbstractSession implements CourseDao{

	@Override
	public void saveCourse(Course course) {
		getSession().persist(course);
	}

	@Override
	public void deleteCourse(Long idCourse) {
		Course course = findCourseById(idCourse);
		if(course != null){
			getSession().delete(course);
		}
	}

	@Override
	public void updateCourse(Course teacher) {
		getSession().update(teacher);
	}

	@Override
	public List<Course> findAllCourses() {
		return getSession().createQuery("from Course").list();
	}

	@Override
	public Course findCourseById(Long idCourse) {
		return (Course)getSession().get(Course.class, idCourse);
	}

	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
		return (Course)getSession().
				createQuery("from Course Where name = :name").
				setParameter("name", name).uniqueResult();
	}

	@Override
	public List<Course> findCourseByIdTeacher(Long idTeacher) {
		// TODO Auto-generated method stub
		return (List<Course>)getSession().createQuery("from Course c JOIN c.teacher t where t.idTeacher =: idTeacher").setParameter("idTeacher", idTeacher).list();
	}

}
