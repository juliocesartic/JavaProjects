package com.platzi.profesoresplatzi.dao;

import java.util.List;

import com.platzi.profesoresplatzi.model.Teacher;



public interface TeacherDao {
void saveTeacher(Teacher teacher);
	
	void deleteTeacher(Long idTeacher);
	
	void updateTeacher(Teacher teacher);
	
	List<Teacher>findAllTeachers();
	
	Teacher findTeacherById(Long idTeacher);
	
	Teacher findTeacherByName(String name);
	
	Teacher findTeacherByIdSocialMedia(Long idSocialMedia);
	
	
	
}
