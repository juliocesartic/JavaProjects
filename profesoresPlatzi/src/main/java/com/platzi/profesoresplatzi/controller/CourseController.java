package com.platzi.profesoresplatzi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.platzi.profesoresplatzi.model.Course;
import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.service.CourseService;
import com.platzi.profesoresplatzi.util.CustomErrorType;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class CourseController {
	
	@Autowired
	CourseService _courseService;
	
	//GET
	@RequestMapping(value = "/courses", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Course>> getCourses(@RequestParam(value = "name", required = false) String name, @RequestParam(value="id_teacher", required = false) Long idTeacher){
		
		List<Course> courses = new ArrayList<Course>();
		
		if(idTeacher != null){
			courses = _courseService.findCourseByIdTeacher(idTeacher);
			
			if(courses.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		
		if(name != null){
			Course course = _courseService.findCourseByName(name);
			
			if(course == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			courses.add(course);
		}
		
		if(idTeacher == null && name == null){
			courses = _courseService.findAllCourses();
			
			if(courses.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		
		/*if(name == null){
			courses = _courseService.findAllCourses();
			if(courses.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		}else{
			Course course = _courseService.findCourseByName(name);
			if(course == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			courses.add(course);
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		}*/

	}
	
	//GET with id
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Course>getCourseById(@PathVariable("id")Long idCourse){
		if(idCourse == null || idCourse <= 0){
			return new ResponseEntity(new CustomErrorType("The idCourse is required"), HttpStatus.CONFLICT);
		}
		Course course = _courseService.findCourseById(idCourse);
		if(course == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}
	
	//ṔOST
	@RequestMapping(value = "/courses", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createCourses(@RequestBody Course course, UriComponentsBuilder uriComponentsBuilder ){
		
		if(_courseService.findCourseByName(course.getName()) != null){
			return new ResponseEntity(new CustomErrorType("Unable to create. A course with name " + course.getName() + " already exists."), HttpStatus.CONFLICT);
		}
		
		if(course.getName().equals(null)|| course.getName().isEmpty()){
			return new ResponseEntity(new CustomErrorType("The course's name is required."), HttpStatus.CONFLICT);
		}
		
		_courseService.saveCourse(course);
		

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/v1/Courses/{id}").buildAndExpand(course.getIdCourse()).toUri());
	
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	//UPDATE
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Course> updateCourses(@PathVariable("id")Long idCourse, @RequestBody Course course){
		
		Course currentCourse = _courseService.findCourseById(idCourse);
		
		if(currentCourse == null){
			return new ResponseEntity( HttpStatus.NO_CONTENT);
		}
		
		//currentCourse.setTeacher(course.getTeacher());
		currentCourse.setName(course.getName());
		currentCourse.setThemes(course.getThemes());
		currentCourse.setProject(course.getProject());
		
		_courseService.updateCourse(currentCourse);
		return new ResponseEntity<Course>(currentCourse, HttpStatus.OK);
		
	}
	
	
	//DELETE
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteCourse(@PathVariable("id")Long idCourse){

		Course course = _courseService.findCourseById(idCourse);
		
		if(course == null){
			System.out.println("Unable to delete, Course not found");
			return new ResponseEntity( new CustomErrorType("Unable to delete. Course with Id: " +  idCourse +  " not found."), HttpStatus.NOT_FOUND);
		}

		
		_courseService.deleteCourse(idCourse);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		
	}
	


}
