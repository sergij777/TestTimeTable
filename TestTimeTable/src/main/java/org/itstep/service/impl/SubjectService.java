package org.itstep.service.impl;

import org.itstep.model.Subject;

public interface SubjectService {
	
	Subject save(Subject subject);
	 
	 Subject update(Subject subject);
	 
	 Subject get(String subjectName);
	 
	 void delete(Subject subject); 

}
