package com.lab01.demo.controller;

import com.lab01.demo.model.User;
import com.lab01.demo.service.LoginService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(@ModelAttribute("User") @NotNull User user, Model model){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            model.addAttribute("error", "Username and password are required!");
            return "/home/index";
        }

        if(LoginService.login(user.getUsername(), user.getPassword())){
            model.addAttribute("success", "Login Succeed");
            return "/home/index";
        }
        model.addAttribute("error", "Invalid username and password!");
        return "/home/index";
    }
}
