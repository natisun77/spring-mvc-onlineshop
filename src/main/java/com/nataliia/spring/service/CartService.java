package com.nataliia.spring.service;

import com.nataliia.spring.model.Product;

import java.util.List;
import java.util.Optional;

public interface CartService {

    boolean addProductToCart(long cartId, long productId);

    Optional<List<Product>> getAll(long cartId);

    void deleteProductFromCart(long cartId, long productId);

    void cleanAll(long cartId);
}
