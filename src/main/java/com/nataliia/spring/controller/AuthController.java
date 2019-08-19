package com.nataliia.spring.controller;

import com.nataliia.spring.model.User;
import com.nataliia.spring.model.UserPayload;
import com.nataliia.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("userRegistrationPayload", new UserPayload());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView register(@Valid @ModelAttribute UserPayload userPayload,
                                 BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addAllObjects(bindingResult.getModel());
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        userService.add(User.of(userPayload));
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
