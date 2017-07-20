package com.platzi.profesoresplatzi.dao;

import java.util.List;

import com.platzi.profesoresplatzi.model.Teacher;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

public interface TeacherSocialMediaDao {

	void saveTeacherSocialMedia(TeacherSocialMedia teacherSocialMedia);
	
	void deleteTeacherSocialMedia(Long idTeacherSocialMedia);
	
	void updateTeacherSocialMedia(TeacherSocialMedia teacherSocialMedia);
	
	List<TeacherSocialMedia>findAllTeacherSocialMedias();
	
	TeacherSocialMedia findTeacherSocialMediaById(Long idTeacherSocialMedia);
}
