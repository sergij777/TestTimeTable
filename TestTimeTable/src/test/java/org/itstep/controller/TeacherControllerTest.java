package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.ApplicationRunner;
import org.itstep.dao.LessonDAO;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.itstep.service.LessonService;
import org.itstep.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class TeacherControllerTest {
	
	@Autowired
	TestRestTemplate restTemplate;

	
	@MockBean
	TeacherService teacherService;

	@Test
	public void testSave() throws URISyntaxException {
		Teacher teacher = new Teacher();
		
		Subject subject = new Subject();
		subject.setName("Java");
		
		teacher.setSubject(subject);
		
		Mockito.when(teacherService.save(Mockito.any(Teacher.class))).thenReturn(teacher);
		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teacher, HttpMethod.POST, new URI("/teacher"));
		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Java", response.getBody().getSubject().getName());
		
		
		Mockito.verify(teacherService, Mockito.times(1)).save(Mockito.any(Teacher.class));
	}

//	@Test
//	public void testUpdate() {
//        Teacher teacher = new Teacher();
//		
//		Subject subject = new Subject();
//		subject.setName("Java");
//		
//		teacher.setSubject(subject);
//		
//		Mockito.when(teacherService.update(Mockito.any(Teacher.class))).thenReturn(teacher);
//		RequestEntity request = new RequestEntity(teacher, HttpMethod.PUT, "/teacher");
//		ResponseEntity response = restTemplate.exchange(request, Teacher.class);
//		
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//	//	assertEquals("Java", response.getSubject().getName());
//		
//		
//		Mockito.verify(teacherService, Mockito.times(1)).save(Mockito.any(Teacher.class));
//		
//	}
//
//	@Test
//	public void testGetOne() {
//		Teacher teacher = new Teacher();		
//			
//		teacher.setLogin("Alex");
//		
//		Mockito.when(teacherService.get(Mockito.anyString())).thenReturn(teacher);
//		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teacher, HttpMethod.GET, new URI("/get-one/teacher"));
//		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
//		
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals("Java", response.getBody().getLogin());
//		
//		
//		Mockito.verify(teacherService, Mockito.times(1)).save(Mockito.any(Teacher.class));
//	}

}
