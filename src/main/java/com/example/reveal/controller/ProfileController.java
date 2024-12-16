package com.example.reveal.controller;

import com.example.reveal.config.CustomUserDetails;
import com.example.reveal.domain.User;
import com.example.reveal.service.MessageService;
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
public class ProfileController {
    UserService userService;
    MessageService messageService;

    @GetMapping("/profile/posts/{name}")
    public String profilePage(
            Model model,
            @PathVariable String name,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        // currentUser - пользователь, на страницу которого мы перешли
        Optional<User> currentUser = userService.findByUserName(name);
        if (currentUser.isEmpty()) return "redirect:/home";
        model.addAttribute("messages", messageService.findByAuthorUserName(name));
        model.addAttribute("name", name);
        model.addAttribute("isFollower", userService.isFollower(userDetails.getUser(), currentUser.get()));
        model.addAttribute("content", "posts");
        return "/profile/template/profile";
    }

    @GetMapping("profile/about/{name}")
    public String about(
            Model model,
            @PathVariable String name
    ){
        model.addAttribute("content", "about");
        model.addAttribute("biography", userService.findByUserName(name).get().getBiography());
        return "/profile/template/profile";
    }

    @GetMapping("profile/followers/{name}")
    public String followers(
            Model model,
            @PathVariable String name
    ){
        model.addAttribute("content", "followers");
        model.addAttribute("followers", userService.findByUserName(name).get().getSubscribers());
        return "/profile/template/profile";
    }

    @GetMapping("profile/followings/{name}")
    public String followings(
            Model model,
            @PathVariable String name
    ){
        model.addAttribute("content", "followings");
        model.addAttribute("followings", userService.findByUserName(name).get().getSubscriptions());
        return "/profile/template/profile";
    }
}
