package com.courseregistration.config;

import com.courseregistration.entity.Category;
import com.courseregistration.entity.Course;
import com.courseregistration.entity.Role;
import com.courseregistration.entity.Student;
import com.courseregistration.repository.CategoryRepository;
import com.courseregistration.repository.CourseRepository;
import com.courseregistration.repository.RoleRepository;
import com.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create Roles
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role studentRole = new Role();
            studentRole.setName("STUDENT");
            roleRepository.save(studentRole);

            System.out.println("Roles created: ADMIN, STUDENT");
        }

        // Create Admin User
        if (!studentRepository.existsByUsername("admin")) {
            Student admin = new Student();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleRepository.findByName("ADMIN").get());
            admin.setRoles(adminRoles);

            studentRepository.save(admin);
            System.out.println("Admin user created: username=admin, password=admin123");
        }

        // Create Sample Student
        if (!studentRepository.existsByUsername("student1")) {
            Student student = new Student();
            student.setUsername("student1");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setEmail("student1@example.com");

            Set<Role> studentRoles = new HashSet<>();
            studentRoles.add(roleRepository.findByName("STUDENT").get());
            student.setRoles(studentRoles);

            studentRepository.save(student);
            System.out.println("Sample student created: username=student1, password=student123");
        }

        // Create Categories
        if (categoryRepository.count() == 0) {
            Category programming = new Category();
            programming.setName("Programming");
            categoryRepository.save(programming);

            Category database = new Category();
            database.setName("Database");
            categoryRepository.save(database);

            Category webDev = new Category();
            webDev.setName("Web Development");
            categoryRepository.save(webDev);

            Category ai = new Category();
            ai.setName("Artificial Intelligence");
            categoryRepository.save(ai);

            System.out.println("Categories created");
        }

        // Create Sample Courses
        if (courseRepository.count() == 0) {
            Category programming = categoryRepository.findAll().get(0);
            Category database = categoryRepository.findAll().get(1);
            Category webDev = categoryRepository.findAll().get(2);
            Category ai = categoryRepository.findAll().get(3);

            Course[] courses = {
                new Course(null, "Java Programming", "https://picsum.photos/400/200?random=1", 3, "Dr. Nguyen Van A", programming, null),
                new Course(null, "Spring Boot Development", "https://picsum.photos/400/200?random=2", 4, "Dr. Tran Thi B", webDev, null),
                new Course(null, "Database Management", "https://picsum.photos/400/200?random=3", 3, "Prof. Le Van C", database, null),
                new Course(null, "Web Application Development", "https://picsum.photos/400/200?random=4", 4, "Dr. Pham Thi D", webDev, null),
                new Course(null, "Python for Data Science", "https://picsum.photos/400/200?random=5", 3, "Dr. Hoang Van E", programming, null),
                new Course(null, "Machine Learning Basics", "https://picsum.photos/400/200?random=6", 4, "Prof. Nguyen Thi F", ai, null),
                new Course(null, "SQL and NoSQL Databases", "https://picsum.photos/400/200?random=7", 3, "Dr. Vo Van G", database, null),
                new Course(null, "React and Angular", "https://picsum.photos/400/200?random=8", 4, "Dr. Do Thi H", webDev, null),
                new Course(null, "Advanced Java", "https://picsum.photos/400/200?random=9", 4, "Prof. Bui Van I", programming, null),
                new Course(null, "Deep Learning", "https://picsum.photos/400/200?random=10", 4, "Dr. Dang Thi J", ai, null),
                new Course(null, "RESTful API Design", "https://picsum.photos/400/200?random=11", 3, "Dr. Truong Van K", webDev, null),
                new Course(null, "Data Structures and Algorithms", "https://picsum.photos/400/200?random=12", 4, "Prof. Ly Thi L", programming, null)
            };

            for (Course course : courses) {
                courseRepository.save(course);
            }

            System.out.println("Sample courses created: " + courses.length + " courses");
        }
    }
}
