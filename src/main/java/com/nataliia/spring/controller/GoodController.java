package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.service.GoodService;
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
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/add")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("good", new Good());
        modelAndView.setViewName("add-good");
        return modelAndView;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Good good, ModelAndView modelAndView) {
        goodService.add(good);
        List<Good> goods = goodService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("goods");
        return modelAndView;
    }


    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView) {
        List<Good> goods = goodService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("goods");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getGoodForUpdate(@RequestParam Long id, ModelAndView modelAndView) {
        Optional<Good> good = goodService.getById(id);
        good.ifPresentOrElse(g -> {
            modelAndView.addObject(g);
            modelAndView.setViewName("edit-good");
        }, () -> modelAndView.setViewName("redirect:good/all"));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@Valid @ModelAttribute Good good, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addAllObjects(bindingResult.getModel());
            modelAndView.setViewName("edit-good");
            return modelAndView;
        }
        goodService.update(good);
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
        goodService.deleteById(id);
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }
}
