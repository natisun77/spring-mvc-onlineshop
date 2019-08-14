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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute User user, ModelAndView modelAndView) {
        userService.add(user);
        List<User> users = userService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView) {
        List<User> users = userService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getUserForUpdate(@RequestParam Long id, ModelAndView modelAndView) {
        Optional<User> user = userService.getById(id);
        user.ifPresentOrElse(u -> {
            modelAndView.addObject(UserPayload.fromUser(u));
            modelAndView.setViewName("edit-user");
        }, () -> modelAndView.setViewName("redirect:/user/all"));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@Valid @ModelAttribute UserPayload userPayload, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addAllObjects(bindingResult.getModel());
            modelAndView.setViewName("edit-user");
            return modelAndView;
        }
        userService.update(userPayload);
        modelAndView.setViewName("redirect:/user/all");
        return modelAndView;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/user/all");
        return modelAndView;
    }
}
