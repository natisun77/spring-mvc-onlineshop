package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.CartDao;
import com.nataliia.spring.dao.ProductDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Product;
import com.nataliia.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final ProductDao productDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    @Transactional
    @Override
    public boolean addProductToCart(long cartId, long productId) {
        Cart cart = cartDao.findById(cartId);
        if (cart == null) {
            return false;
        }
        Product product = new Product();
        product.setId(productId);
        cart.addProduct(product);
        cartDao.update(cart);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Product>> getAll(long cartId) {
        return Optional.of(cartDao.loadAllProductsFromCart(cartId));
    }

    @Transactional
    @Override
    public void deleteProductFromCart(long cartId, long productId) {
        Cart cart = cartDao.findById(cartId);
        Product product = productDao.findById(productId);
        cart.deleteProduct(product);
        cartDao.update(cart);
    }

    @Transactional
    @Override
    public void cleanAll(long cartId) {
        Cart cart = cartDao.findById(cartId);
        cart.resetCart();
        cartDao.update(cart);
    }
}
