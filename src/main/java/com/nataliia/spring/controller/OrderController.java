package com.nataliia.spring.controller;

import com.nataliia.spring.model.Good;
import com.nataliia.spring.model.Order;
import com.nataliia.spring.service.CartService;
import com.nataliia.spring.service.OrderService;
import com.nataliia.spring.service.UserService;
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

    private OrderService orderService;
    private UserService userService;
    private CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public ModelAndView register(ModelAndView mw, HttpSession httpSession) {
        Long cartId = (Long) (httpSession.getAttribute("userId"));
        if (cartId != null) {
            List<Good> goods = cartService.getAll(cartId).orElse(Collections.emptyList());
            mw.addObject("goodsSize", goods.size());
            mw.addObject("totalAmount", goods.stream().mapToDouble(Good::getPrice).sum());
            mw.addObject("order", new Order());
            mw.setViewName("order");
        } else {
            mw.setViewName("cart");
        }
        return mw;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Order order, ModelAndView mw, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("userId");

        if (userId != null) {
            orderService.addOrder(order, userId);
            mw.setViewName("confirmation");
        } else {
            mw.setViewName("redirect:/cart");
        }

        return mw;
    }

}
