package com.courseregistration.security;

import com.courseregistration.entity.Role;
import com.courseregistration.entity.Student;
import com.courseregistration.repository.RoleRepository;
import com.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if user already exists
        Student student = studentRepository.findByEmail(email).orElse(null);

        if (student == null) {
            // Create new student from Google account
            student = new Student();
            student.setUsername(email);
            student.setEmail(email);
            student.setPassword(""); // No password for OAuth2 users

            Role studentRole = roleRepository.findByName("STUDENT")
                    .orElseThrow(() -> new RuntimeException("STUDENT role not found!"));

            Set<Role> roles = new HashSet<>();
            roles.add(studentRole);
            student.setRoles(roles);

            studentRepository.save(student);
        }

        return oAuth2User;
    }
}
