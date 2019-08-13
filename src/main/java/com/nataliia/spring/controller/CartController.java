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

    @Autowired
    private CartService cartService;

    @GetMapping("/addGood")
    private ModelAndView add(@RequestParam Long goodId, ModelAndView mw, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId != null && cartService.addGoodToCart(userId, goodId)) {
            mw.setViewName("redirect:/good/all");
        } else {
            mw.setViewName("redirect:login");
        }

        return mw;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView mw, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId != null) {
            List<Good> goodsInCart = cartService.getAll(userId).orElseGet(Collections::emptyList);
            mw.addObject("goodsInCart", goodsInCart);
            mw.setViewName("cart");
        } else {
            mw.setViewName("redirect:/good/all");
        }
        return mw;
    }

    @GetMapping("/deleteGood")
    private ModelAndView delete(@RequestParam Long goodId, ModelAndView mw, HttpSession httpSession) {
        Long cartId = (Long) httpSession.getAttribute("userId");
        if (cartId != null) {
            cartService.deleteGoodFromCart(cartId, goodId);
        }
        mw.setViewName("redirect:/cart/all");
        return mw;
    }

    @GetMapping("/deleteAllGoods")
    private ModelAndView deleteAll(ModelAndView mw, HttpSession httpSession) {
        Long cartId = (Long) httpSession.getAttribute("userId");
        if (cartId != null) {
            cartService.cleanAll(cartId);
        }
        mw.setViewName("redirect:/cart/all");
        return mw;
    }
}
