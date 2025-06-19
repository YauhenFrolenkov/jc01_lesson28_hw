package com.edu.educational_system.controller.impl;

import java.util.Optional;

import com.edu.educational_system.controller.Command;

import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceExeption;
import com.edu.educational_system.service.CourseServiceProvider;
import com.edu.educational_system.util.PersonInputParser;

public class RegisterPerson implements Command {
	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService service = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {
		String response;
		try {
			String[] params = request.split("\n");

			if (params.length < 5) {
				return "Insufficient data. Expected: course name, name, email, role, additional parameters.";
			}

			String courseName = params[1].trim();

			Optional<Course> courseOptional = service.findCourseByName(courseName);
			if (courseOptional.isEmpty()) {
				return "Course with the name '" + courseName + "' not found.";
			}
			Course course = courseOptional.get();

			Person person = PersonInputParser.parsePersonFromParams(params);

			service.enrollPerson(course, person);
			response = "User " + person.getName() + " successfully registered for the course '" + courseName + "' as "
					+ params[4].trim() + ".";

		} catch (IllegalArgumentException e) {
			response = "Invalid input data. Please check the entered information.";
		} catch (CourseServiceExeption e) {
			response = "Error while registering user";
		}

		return response;
	}
}
