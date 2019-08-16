package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.model.Order;
import com.nataliia.spring.model.User;
import com.nataliia.spring.service.CartService;
import com.nataliia.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ModelAndView register(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        List<Good> goods = cartService.getAll(user.getId()).orElse(Collections.emptyList());
        modelAndView.addObject("goodsSize", goods.size());
        modelAndView.addObject("totalAmount", goods.stream().mapToDouble(Good::getPrice).sum());
        modelAndView.addObject("order", new Order());
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Order order, ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        orderService.addOrder(order, user.getId());
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
}
