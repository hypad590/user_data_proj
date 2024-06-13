package com.hypad.buysell.controller;

import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    private final AuthService authService;

    public HomePageController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String homePage() {
        return "homePage";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        authService.deleteUser(id);
        return "redirect:/";
    }
}
