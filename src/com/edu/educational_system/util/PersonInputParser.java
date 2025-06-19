package com.edu.educational_system.util;

import com.edu.educational_system.model.Administrator;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.model.PersonType;
import com.edu.educational_system.model.Student;
import com.edu.educational_system.model.Teacher;

public class PersonInputParser {
	 public static Person parsePersonFromParams(String[] params) throws IllegalArgumentException {
	        if (params.length < 5) {
	            throw new IllegalArgumentException("Insufficient data for person creation.");
	        }
	        
	        String personName = params[2].trim();
	        String email = params[3].trim();
	        String role = params[4].trim();

	        PersonType personType = PersonTypeParser.fromString(role);
	        
	        switch (personType) {
            case STUDENT -> {
                if (params.length < 7) {
                    throw new IllegalArgumentException("For a student, group and average grade must be specified.");
                }
                String group = params[5].trim();
                double avgGrade = Double.parseDouble(params[6]);
                return new Student(personName, email, group, avgGrade);
            }
            case TEACHER -> {
                if (params.length < 6) {
                    throw new IllegalArgumentException("For an instructor, the subject must be specified.");
                }
                String subject = params[5].trim();
                return new Teacher(personName, email, subject);
            }
            case ADMINISTRATOR -> {
                if (params.length < 6) {
                    throw new IllegalArgumentException("For an administrator, the department must be specified.");
                }
                String department = params[5].trim();
                return new Administrator(personName, email, department);
            }
            default -> throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}


