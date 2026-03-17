package com.courseregistration.service;

import com.courseregistration.dto.StudentRegistrationDto;
import com.courseregistration.entity.Role;
import com.courseregistration.entity.Student;
import com.courseregistration.repository.RoleRepository;
import com.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Student registerStudent(StudentRegistrationDto dto) {
        if (studentRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Student student = new Student();
        student.setUsername(dto.getUsername());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        student.setEmail(dto.getEmail());

        // Assign STUDENT role by default
        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("STUDENT role not found!"));

        Set<Role> roles = new HashSet<>();
        roles.add(studentRole);
        student.setRoles(roles);

        return studentRepository.save(student);
    }

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElse(null);
    }
}
