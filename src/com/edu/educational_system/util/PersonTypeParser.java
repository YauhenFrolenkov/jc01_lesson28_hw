package com.edu.educational_system.util;

import com.edu.educational_system.model.PersonType;

public class PersonTypeParser {

	public static PersonType fromString(String value) {
		if (value == null) {
			return null;
		}
		return switch (value.toLowerCase()) {
		case "student" -> PersonType.STUDENT;
		case "teacher" -> PersonType.TEACHER;
		case "administrator" -> PersonType.ADMINISTRATOR;
		case "course" -> PersonType.COURSE;
		default -> null;
		};
	}

	public static String toFileString(PersonType type) {
		return switch (type) {
		case STUDENT -> "Student";
		case TEACHER -> "Teacher";
		case ADMINISTRATOR -> "Administrator";
		case COURSE -> "Course";
		};
	}

}
