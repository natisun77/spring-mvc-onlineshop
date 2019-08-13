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

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute User user, ModelAndView mw) {
        userService.add(user);
        List<User> users = userService.getAll().orElseGet(Collections::emptyList);
        mw.addObject("users", users);
        mw.setViewName("users");
        return mw;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView mw) {
        List<User> users = userService.getAll().orElseGet(Collections::emptyList);
        mw.addObject("users", users);
        mw.setViewName("users");
        return mw;
    }

    @GetMapping("/edit")
    public ModelAndView getUserForUpdate(@RequestParam Long id, ModelAndView mw) {
        Optional<User> user = userService.getById(id);
        user.ifPresentOrElse(u -> {
            mw.addObject(UserPayload.fromUser(u));
            mw.setViewName("edit-user");
        }, () -> mw.setViewName("redirect:/user/all"));
        return mw;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@Valid @ModelAttribute UserPayload userPayload, ModelAndView mw, BindingResult br) {
        if (br.hasErrors()) {
            mw.addAllObjects(br.getModel());
            mw.setViewName("edit-user");
            return mw;
        }
        userService.update(userPayload);
        mw.setViewName("redirect:/user/all");
        return mw;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam Long id, ModelAndView mw) {
        userService.deleteById(id);
        mw.setViewName("redirect:/user/all");
        return mw;
    }
}
