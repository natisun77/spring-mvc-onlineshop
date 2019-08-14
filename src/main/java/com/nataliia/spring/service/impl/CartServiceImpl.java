package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.CartDao;
import com.nataliia.spring.dao.GoodDao;
import com.nataliia.spring.model.Cart;
import com.nataliia.spring.model.Good;
import com.nataliia.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final GoodDao goodDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao, GoodDao goodDao) {
        this.cartDao = cartDao;
        this.goodDao = goodDao;
    }

    @Transactional
    @Override
    public boolean addGoodToCart(long cartId, long goodId) {
        Cart cart = cartDao.findById(cartId);
        if (cart == null) {
            return false;
        }
        Good good = new Good();
        good.setId(goodId);
        cart.addGood(good);
        cartDao.update(cart);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Good>> getAll(long cartId) {
        return Optional.of(cartDao.loadAllGoodsFromCart(cartId));
    }

    @Transactional
    @Override
    public void deleteGoodFromCart(long cartId, long goodId) {
        Cart cart = cartDao.findById(cartId);
        Good good = goodDao.findById(goodId);
        cart.deleteGood(good);
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
