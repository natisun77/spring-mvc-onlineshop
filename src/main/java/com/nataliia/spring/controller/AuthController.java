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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public ModelAndView register(ModelAndView mw) {
        mw.addObject("userRegistrationPayload", new UserPayload());
        mw.setViewName("registration");
        return mw;
    }

    @PostMapping(value = "/registration")
    public ModelAndView register(@Valid @ModelAttribute UserPayload urp, BindingResult br, ModelAndView mw) {
        if (br.hasErrors()) {
            mw.addAllObjects(br.getModel());
            mw.setViewName("registration");
            return mw;
        }
        userService.add(User.of(urp));
        mw.addObject("user", new User());
        mw.setViewName("index");
        return mw;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mw) {
        mw.setViewName("index");
        return mw;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user, ModelAndView mw, HttpSession httpSession) {
        userService.getUserByEmailAndPassword(
                user.getEmail(), user.getPassword())
                .ifPresentOrElse(u -> {
                    httpSession.setAttribute("userId", u.getId());
                    mw.setViewName("redirect:user/all");
                }, () -> mw.setViewName("index"));
        return mw;
    }
}
