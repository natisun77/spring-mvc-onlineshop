package com.nataliia.spring.dao;

import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Product;

import java.util.List;

public interface CartDao {

    Cart findById(long id);

    void update(Cart cart);

    List<Product> loadAllProductsFromCart(Long id);

}
