package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    private ModelAndView add(@RequestParam Long goodId, ModelAndView modelAndView, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId != null && cartService.addGoodToCart(userId, goodId)) {
            modelAndView.setViewName("redirect:/good/all");
        } else {
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId != null) {
            List<Good> goodsInCart = cartService.getAll(userId).orElseGet(Collections::emptyList);
            modelAndView.addObject("goodsInCart", goodsInCart);
            modelAndView.setViewName("cart");
        } else {
            modelAndView.setViewName("redirect:/good/all");
        }
        return modelAndView;
    }

    @GetMapping("/deleteGood")
    private ModelAndView delete(@RequestParam Long goodId, ModelAndView modelAndView, HttpSession httpSession) {
        Long cartId = (Long) httpSession.getAttribute("userId");
        if (cartId != null) {
            cartService.deleteGoodFromCart(cartId, goodId);
        }
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }

    @GetMapping("/deleteAllGoods")
    private ModelAndView deleteAll(ModelAndView modelAndView, HttpSession httpSession) {
        Long cartId = (Long) httpSession.getAttribute("userId");
        if (cartId != null) {
            cartService.cleanAll(cartId);
        }
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }
}
