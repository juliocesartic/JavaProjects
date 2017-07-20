package com.platzi.profesoresplatzi.dao;

import java.util.List;

import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

public class TeacherSocialMediaImpl extends AbstractSession implements TeacherSocialMediaDao{

	@Override
	public void saveTeacherSocialMedia(TeacherSocialMedia teacherSocialMedia) {
		getSession().persist(teacherSocialMedia);
	}

	@Override
	public void deleteTeacherSocialMedia(Long idTeacherSocialMedia) {
		
		TeacherSocialMedia teacherSocialMedia = findTeacherSocialMediaById(idTeacherSocialMedia);
		
		if(teacherSocialMedia != null){
			getSession().delete(teacherSocialMedia);
		}
	}

	@Override
	public void updateTeacherSocialMedia(TeacherSocialMedia teacherSocialMedia) {
		getSession().update(teacherSocialMedia);
		
	}

	@Override
	public List<TeacherSocialMedia> findAllTeacherSocialMedias() {
		return getSession().createQuery("From TeacherSocialMedia").list();
	}

	@Override
	public TeacherSocialMedia findTeacherSocialMediaById(Long idTeacherSocialMedia) {
		
		return getSession().get(TeacherSocialMedia.class, idTeacherSocialMedia);
	}

}
