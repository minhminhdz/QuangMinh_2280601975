package com.courseregistration.service;

import com.courseregistration.entity.Course;
import com.courseregistration.entity.Enrollment;
import com.courseregistration.entity.Student;
import com.courseregistration.repository.CourseRepository;
import com.courseregistration.repository.EnrollmentRepository;
import com.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void enrollCourse(String username, Long courseId) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if already enrolled
        if (enrollmentRepository.existsByStudentAndCourseId(student, courseId)) {
            throw new RuntimeException("You are already enrolled in this course!");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());

        enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getStudentEnrollments(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return enrollmentRepository.findByStudent(student);
    }
}
