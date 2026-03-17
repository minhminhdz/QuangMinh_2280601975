package com.courseregistration.controller;

import com.courseregistration.entity.Enrollment;
import com.courseregistration.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/enroll")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping("/course/{id}")
    public String enrollCourse(@PathVariable Long id,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        try {
            String username = authentication.getName();
            enrollmentService.enrollCourse(username, id);
            redirectAttributes.addFlashAttribute("success", "Successfully enrolled in the course!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/home";
    }

    @GetMapping("/my-courses")
    public String myCourses(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(username);
        model.addAttribute("enrollments", enrollments);
        return "my-courses";
    }
}
