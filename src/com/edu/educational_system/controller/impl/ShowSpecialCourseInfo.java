package com.edu.educational_system.controller.impl;

import java.util.List;
import java.util.Optional;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.model.Administrator;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.model.Teacher;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceExeption;
import com.edu.educational_system.service.CourseServiceProvider;

public class ShowSpecialCourseInfo implements Command {
	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService service = courseServiceProvider.getCourseService();
	
	@Override
	public String execute(String request) {
		String response;
	    String[] lines = request.split("\n");
	    
	    if (lines.length < 2) {
	        return "Invalid request. Please specify the course name.";
	    }

	    String courseName = lines[1].trim();
	    
	    try {
            Optional<Course> optionalCourse = service.findCourseByName(courseName);

            if (optionalCourse.isEmpty()) {
                return "Course with the name '" + courseName + "' not found.";
            }

            Course course = optionalCourse.get();
            StringBuilder sb = new StringBuilder("Course information: ").append(courseName).append("\n");

            List<Person> participants = course.getParticipants();
            sb.append("\nStudents:\n");
            boolean hasStudents = false;
            for (Person p : participants) {
                sb.append("- ").append(p.getName()).append(" (").append(p.getEmail()).append(")\n");
                hasStudents = true;
            }
            if (!hasStudents) {
                sb.append("No students.\n");
            }

            List<Person> staff = course.getStaff();
            sb.append("\nInstructors:\n");
            boolean hasTeachers = false;
            for (Person p : staff) {
                if (p instanceof Teacher) {
                    sb.append("- ").append(p.getName()).append(" (").append(p.getEmail()).append(")\n");
                    hasTeachers = true;
                }
            }
            if (!hasTeachers) {
                sb.append("No instructors.\n");
            }

            sb.append("\nAdministrators:\n");
            boolean hasAdmins = false;
            for (Person p : staff) {
                if (p instanceof Administrator) {
                    sb.append("- ").append(p.getName()).append(" (").append(p.getEmail()).append(")\n");
                    hasAdmins = true;
                }
            }
            if (!hasAdmins) {
                sb.append("No administrators.\n");
            }

            response = sb.toString();
        } catch (CourseServiceExeption e) {
            response = "Error finding course: " + courseName;
        }

        return response;
    }
}
