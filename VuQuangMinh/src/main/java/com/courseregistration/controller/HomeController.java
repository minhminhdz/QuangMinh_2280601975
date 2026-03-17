package com.courseregistration.controller;

import com.courseregistration.entity.Course;
import com.courseregistration.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CourseService courseService;

    @GetMapping({"/", "/home", "/courses"})
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String search,
            Model model) {

        Page<Course> coursePage;

        if (search != null && !search.trim().isEmpty()) {
            coursePage = courseService.searchCourses(search, page, 5);
            model.addAttribute("search", search);
        } else {
            coursePage = courseService.getAllCourses(page, 5);
        }

        model.addAttribute("coursePage", coursePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coursePage.getTotalPages());

        return "home";
    }
}
