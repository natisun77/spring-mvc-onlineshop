package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.model.Order;
import com.nataliia.spring.service.CartService;
import com.nataliia.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public ModelAndView register(ModelAndView modelAndView, HttpSession httpSession) {
        Long cartId = (Long) (httpSession.getAttribute("userId"));
        if (cartId != null) {
            List<Good> goods = cartService.getAll(cartId).orElse(Collections.emptyList());
            modelAndView.addObject("goodsSize", goods.size());
            modelAndView.addObject("totalAmount", goods.stream().mapToDouble(Good::getPrice).sum());
            modelAndView.addObject("order", new Order());
            modelAndView.setViewName("order");
        } else {
            modelAndView.setViewName("cart");
        }
        return modelAndView;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Order order, ModelAndView modelAndView, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");

        if (userId != null) {
            orderService.addOrder(order, userId);
            modelAndView.setViewName("confirmation");
        } else {
            modelAndView.setViewName("redirect:/cart");
        }
        return modelAndView;
    }
}
