package com.example.blog.controllers;

import com.example.blog.models.User;
import com.example.blog.models.UserRole;
import com.example.blog.repositories.Roles;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.UserDetailsLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private UserDetailsLoader userService;
    private Roles rolesRepo;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder, UserDetailsLoader userService, Roles rolesRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.rolesRepo = rolesRepo;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid User user, Errors errors, Model model, @RequestParam String verifyPassword) {
        errors = userService.checkRegistration(user, errors);
        if(!user.getPassword().equals(verifyPassword)) {
            errors.rejectValue(
                    "password",
                    "user.password",
                    "Passwords do not match."
            );
        }
        if(errors.hasErrors()) {
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepo.save(user);
        UserRole roleUser = new UserRole(user.getId(), "AUTHOR");
        rolesRepo.save(roleUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}
