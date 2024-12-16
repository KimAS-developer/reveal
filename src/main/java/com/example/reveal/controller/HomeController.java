package com.example.reveal.controller;

import com.example.reveal.config.CustomUserDetails;
import com.example.reveal.domain.Message;
import com.example.reveal.domain.User;
import com.example.reveal.service.MessageService;
import com.example.reveal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HomeController {

    private MessageService messageService;
    private UserService userService;

    @GetMapping("/home")
    public String mainPage(Model model){
        List<Message> messages = messageService.findAll();
        Collections.reverse(messages);
        model.addAttribute("messages", messages);
        return "/home";
    }

    @GetMapping("/favorites")
    public String favoritesPage(){
        return "/favorites";
    }
}
