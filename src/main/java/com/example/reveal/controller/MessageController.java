package com.example.reveal.controller;

import com.example.reveal.config.CustomUserDetails;
import com.example.reveal.domain.Message;
import com.example.reveal.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MessageController {

    private MessageService service;

    @GetMapping("/new_post")
    public String newPostPage(Model model){
        model.addAttribute("message", new Message());
        return "/new_post";
    }

    @PostMapping("/new_post")
    public String newPostPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute Message message
    ){
        message.setAuthor(userDetails.getUser());
        service.create(message);
        return "redirect:/home";
    }
}
