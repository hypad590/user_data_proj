package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/api/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authorization")
    public String authPage() {
        return "authPage";
    }

    @PostMapping("/authorization")
    public String authPage(@RequestParam String name, @RequestParam String password, Model model) {
        if(authService.ifUserExists(name,password)){
            return "redirect:/";
        }
        else{
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            return "redirect:/register";
        }
    }
}
