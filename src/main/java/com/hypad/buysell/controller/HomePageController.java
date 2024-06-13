package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/findUserById/{id}")
    @ResponseBody
    public List<User> findUserById(@PathVariable("id") Long id) {
        return authService.findUserById(id);
    }

    @PostMapping("/findUserByName/{name}")
    @ResponseBody
    public List<User> findUserByName(@PathVariable("name") String name) {
        return authService.findUserByName(name);
    }

    @PostMapping("/findAllUsers")
    @ResponseBody
    public List<User> findAllUsers() {
        return authService.findAllUsers();
    }
}
