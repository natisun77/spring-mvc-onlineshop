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

    @Autowired
    private GoodService goodService;

    @GetMapping("/add")
    public ModelAndView register(ModelAndView mw) {
        mw.addObject("good", new Good());
        mw.setViewName("add-good");
        return mw;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Good good, ModelAndView mw) {
        goodService.add(good);
        List<Good> goods = goodService.getAll().orElseGet(Collections::emptyList);
        mw.addObject("goods", goods);
        mw.setViewName("goods");
        return mw;
    }


    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView mw) {
        List<Good> goods = goodService.getAll().orElseGet(Collections::emptyList);
        mw.addObject("goods", goods);
        mw.setViewName("goods");
        return mw;
    }

    @GetMapping("/edit")
    public ModelAndView getGoodForUpdate(@RequestParam Long id, ModelAndView mw) {
        Optional<Good> good = goodService.getById(id);
        good.ifPresentOrElse(g -> {
            mw.addObject(g);
            mw.setViewName("edit-good");
        }, () -> mw.setViewName("redirect:good/all"));
        return mw;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@Valid @ModelAttribute Good good, ModelAndView mw, BindingResult br) {
        if (br.hasErrors()) {
            mw.addAllObjects(br.getModel());
            mw.setViewName("edit-good");
            return mw;
        }
        goodService.update(good);
        mw.setViewName("redirect:/good/all");
        return mw;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam Long id, ModelAndView mw) {
        goodService.deleteById(id);
        mw.setViewName("redirect:/good/all");
        return mw;
    }
}
