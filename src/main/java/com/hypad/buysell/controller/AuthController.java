package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth/api/v1")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authorization")
    public String authPage() {
        return "authPage";
    }

    @PostMapping("/authorization")
    public ModelAndView authPage(@RequestBody User user,
                           Model attributes){
        logger.info("Received request to authorize user: {}", user);
        if(authService.ifUserExists(user.getName(),user.getPassword(),user.getGmail())){
            logger.info("User exists, redirecting to home page.");
            return new ModelAndView("redirect:/");
        }
        else{
            attributes.addAttribute("name",user.getName());
            attributes.addAttribute("password", user.getPassword());
            attributes.addAttribute("gmail",user.getGmail());

            authService.saveUser(user);

            ModelAndView mav = new ModelAndView("userSuccessfullyPage");

            mav.addObject("welcomeMsg","Welcome aboard! Your account has been created.");
            mav.addObject("user",user);

            logger.info("User does not exist, creating new user and redirecting to userSuccessfullyPage.");
            return mav;
        }
    }

    @GetMapping("/userSuccessfullyPage")
    public String userCreatedSuccessfully() {
        return "userSuccessfullyPage";
    }

}
