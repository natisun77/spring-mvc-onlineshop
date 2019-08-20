package com.nataliia.spring.controller;

import com.nataliia.spring.model.Product;
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

    @GetMapping("/addProduct")
    private ModelAndView add(@RequestParam Long productId, ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        cartService.addProductToCart(user.getId(), productId);
        modelAndView.setViewName("redirect:/product/all");
        return modelAndView;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        List<Product> productsInCart = cartService.getAll(user.getId()).orElseGet(Collections::emptyList);
        modelAndView.addObject("productsInCart", productsInCart);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping("/deleteProduct")
    private ModelAndView delete(@RequestParam Long productId, ModelAndView modelAndView,
                                @AuthenticationPrincipal User user) {
        cartService.deleteProductFromCart(user.getId(), productId);
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }

    @GetMapping("/deleteAllProducts")
    private ModelAndView deleteAll(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        cartService.cleanAll(user.getId());
        modelAndView.setViewName("redirect:/cart/all");
        return modelAndView;
    }
}
