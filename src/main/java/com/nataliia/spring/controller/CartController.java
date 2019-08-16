package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.model.User;
import com.nataliia.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/addGood")
    private ModelAndView add(@RequestParam Long goodId, ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        cartService.addGoodToCart(user.getId(), goodId);
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        List<Good> goodsInCart = cartService.getAll(user.getId()).orElseGet(Collections::emptyList);
        modelAndView.addObject("goodsInCart", goodsInCart);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/deleteGood")
    private ModelAndView delete(@RequestParam Long goodId, ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        cartService.deleteGoodFromCart(user.getId(), goodId);
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }

    @GetMapping("/deleteAllGoods")
    private ModelAndView deleteAll(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        cartService.cleanAll(user.getId());
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }
}
