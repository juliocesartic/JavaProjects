package com.platzi.profesoresplatzi.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.platzi.profesoresplatzi.model.Teacher;
import com.platzi.profesoresplatzi.model.TeacherSocialMedia;

@Repository
@Transactional
public class TeacherDaoImpl extends AbstractSession implements TeacherDao {
	


	public void saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		getSession().persist(teacher);
		
	}

	public void deleteTeacher(Long idTeacher) {
		Teacher teacher = findTeacherById(idTeacher);
		if(teacher != null){
			
			Iterator<TeacherSocialMedia> i = teacher.getTeacherSocialMedias().iterator();
			while(i.hasNext()){
				TeacherSocialMedia teacherSocialMedia = i.next();
				i.remove();
				
				getSession().delete(teacherSocialMedia);
			}
			
			teacher.getTeacherSocialMedias().clear();
			getSession().delete(teacher);
		}
		
			
	}
		

	public void updateTeacher(Teacher teacher) {
		getSession().update(teacher);
	}

	public List<Teacher> findAllTeachers() {
		return getSession().createQuery("from Teacher").list();
	}


	@Override
	public Teacher findTeacherByName(String name) {
		return (Teacher)getSession().
				createQuery("from Teacher Where name = :name").
				setParameter("name", name).uniqueResult();
	}

	@Override
	public Teacher findTeacherById(Long idTeacher) {
		return (Teacher)getSession().get(Teacher.class, idTeacher);
	}

	@Override
	public Teacher findTeacherByIdSocialMedia(Long idSocialMedia) {
		// TODO Auto-generated method stub
		List<Object[]> objects = getSession().
				createQuery("from Teacher t1 "
						+ "join t1.teacherSocialMedias t2 "
						+ " where t2.socialMedia.idSocialMedia = :idSocialMediaQ ").
				setParameter("idSocialMediaQ", idSocialMedia).list();
		
		if(objects.size() > 0){
			for(Object[]objects2: objects){
				for(Object object : objects2){
					if(object instanceof Teacher){
						return (Teacher)object;
					}
				}
			}
		}
		return null;
		
	}
		
}
