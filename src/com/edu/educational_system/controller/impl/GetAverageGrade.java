package com.edu.educational_system.controller.impl;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceExeption;
import com.edu.educational_system.service.CourseServiceProvider;

public class GetAverageGrade implements Command {
	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService service = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {
		String response;
		String[] lines = request.split("\n");
		if (lines.length < 2) {
			return "Course name not specified.";
		}
		String courseName = lines[1].trim();

		try {
			double grade = service.getAverageGrade(courseName);
			response = "Average grade: " + grade;
		} catch (CourseServiceExeption e) {
			response = "Course with the name '" + courseName + "' not found.";
		}
		return response;
	}

}
