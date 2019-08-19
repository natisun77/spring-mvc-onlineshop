package com.nataliia.spring.controller;

import com.nataliia.spring.model.Product;
import com.nataliia.spring.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("add-product");
        return modelAndView;
    }

    @PostMapping("/add")
    private ModelAndView add(@ModelAttribute Product product, ModelAndView modelAndView) {
        productService.add(product);
        List<Product> products = productService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("products", products);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @GetMapping("/all")
    private ModelAndView getAll(ModelAndView modelAndView) {
        List<Product> products = productService.getAll().orElseGet(Collections::emptyList);
        modelAndView.addObject("products", products);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getProductForUpdate(@RequestParam Long id, ModelAndView modelAndView) {
        Optional<Product> product = productService.getById(id);
        product.ifPresentOrElse(g -> {
            modelAndView.addObject(g);
            modelAndView.setViewName("edit-product");
        }, () -> modelAndView.setViewName("redirect:product/all"));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@Valid @ModelAttribute Product product, ModelAndView modelAndView,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addAllObjects(bindingResult.getModel());
            modelAndView.setViewName("edit-product");
            return modelAndView;
        }
        productService.update(product);
        modelAndView.setViewName("redirect:/product/all");
        return modelAndView;
    }

    @GetMapping("/delete")
    private ModelAndView delete(@RequestParam Long id, ModelAndView modelAndView) {
        productService.deleteById(id);
        modelAndView.setViewName("redirect:/product/all");
        return modelAndView;
    }
}
