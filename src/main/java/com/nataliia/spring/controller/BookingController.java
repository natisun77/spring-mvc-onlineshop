package com.nataliia.spring.controller;

import com.nataliia.spring.model.Booking;
import com.nataliia.spring.model.Product;
import com.nataliia.spring.model.User;
import com.nataliia.spring.service.CartService;
import com.nataliia.spring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final CartService cartService;

    @Autowired
    public BookingController(BookingService bookingService, CartService cartService) {
        this.bookingService = bookingService;
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public ModelAndView register(ModelAndView modelAndView, @AuthenticationPrincipal User user) {
        List<Product> products = cartService.getAll(user.getId()).orElse(Collections.emptyList());
        modelAndView.addObject("productsSize", products.size());
        modelAndView.addObject("totalAmount", products.stream().mapToDouble(Product::getPrice).sum());
        modelAndView.addObject("booking", new Booking());
        modelAndView.setViewName("booking");
        return modelAndView;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Booking booking, ModelAndView modelAndView,
                             @AuthenticationPrincipal User user) {
        bookingService.addBooking(booking, user.getId());
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
}
