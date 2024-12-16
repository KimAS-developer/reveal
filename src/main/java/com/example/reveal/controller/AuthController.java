package com.example.reveal.controller;

import com.example.reveal.domain.ResultUserCreation;
import com.example.reveal.domain.User;
import com.example.reveal.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class AuthController {
    private UserService service;

    @GetMapping("/login")
    public String loginPage(Model model, HttpSession session){
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("user", new User());
        return "/auth/registration";
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute User user){
        return "/main";
    }

    @PostMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/auth/registration";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords don't match");
            return "/auth/registration";
        }

        // Trying to create the user
        ResultUserCreation resultUserCreation = service.create(user);
        if (resultUserCreation == ResultUserCreation.USER_NAME_IS_USED){
            model.addAttribute("nameError", "This username already exists");
            return "/auth/registration";
        }

        if (resultUserCreation == ResultUserCreation.USER_EMAIL_IS_USED){
            model.addAttribute("emailError", "A user with this email already exists");
            return "/auth/registration";
        }

        // Successful creation
        model.addAttribute("success", true);
        return "/auth/registration";
    }
}