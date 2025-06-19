package com.edu.educational_system.repository;

import com.edu.educational_system.repository.impl.FileCourseRepository;

public final class CourseRepositoryProvider {
	private static final CourseRepositoryProvider instance = new CourseRepositoryProvider();
	
	private final CourseRepository courseRepository = new FileCourseRepository();

	private CourseRepositoryProvider() {
	}

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}
	
	public static CourseRepositoryProvider getInstance() {
		return instance;
	}
	

}
