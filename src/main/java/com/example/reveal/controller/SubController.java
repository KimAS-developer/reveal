package com.example.reveal.controller;

import com.example.reveal.config.CustomUserDetails;
import com.example.reveal.domain.User;
import com.example.reveal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class SubController {
    UserService userService;

    @GetMapping("/subscribe/{name}")
    public String subscribe(
            Model model,
            @PathVariable String name,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        Optional<User> currentUser = userService.findByUserName(name);
        if (currentUser.isEmpty()) return "redirect:/home";
        userService.subscribe(userDetails.getUser(), currentUser.get());
        model.addAttribute("isFollower", userService.isFollower(userDetails.getUser(), currentUser.get()));
        return "redirect:/profile/posts/" + name;
    }

    @GetMapping("/unsubscribe/{name}")
    public String unsubscribe(
            Model model,
            @PathVariable String name,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        Optional<User> currentUser = userService.findByUserName(name);
        if (currentUser.isEmpty()) return "redirect:/home";
        userService.unsubscribe(userDetails.getUser(), currentUser.get());
        model.addAttribute("isFollower", userService.isFollower(userDetails.getUser(), currentUser.get()));
        return "redirect:/profile/posts/" + name;
    }
}
