package org.itstep.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeacherDAOTest {
	
	 Subject subjectInDB;
	 Teacher teacherInDB;

	 @Autowired
	 TeacherDAO teacherDAO;

	 @Autowired
	 SubjectDAO subjectDAO;
	 
	 @Before
	 public void setPreData() {

	  Subject testSubject = new Subject();
	  
	  testSubject.setName("JavaQA");
	  
	  subjectInDB = subjectDAO.save(testSubject);
	  
	  Teacher teacher = new Teacher();	  
	  
	  teacher.setLogin("Ignatenko2207");
	  teacher.setPassword("123456");
	  teacher.setFirstName("Alex");
	  teacher.setSecondName("Ignatenko");
	  teacher.setSubject(subjectInDB);
	  
	  teacherInDB = teacherDAO.save(teacher);	  
	 }

	@Test
	public void testFindAllBySubject() {
		
		List<Teacher> teachers = teacherDAO.findAllBySubject(subjectInDB.getName());
		
		assertNotNull(teachers);
		
		assertEquals(1, teachers.size());
		
		assertEquals(teachers.get(0).getLogin(), "Ignatenko2207");
		
		assertEquals(teachers.get(0).getSubject().getName(), "JavaQA");
	}
	
	 @After
	 public void cleanDB() {
	  teacherDAO.delete(teacherInDB);
	  subjectDAO.delete(subjectInDB);
	 }
}
