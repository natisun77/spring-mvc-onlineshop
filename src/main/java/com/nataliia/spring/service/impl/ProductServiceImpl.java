package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.ProductDao;
import com.nataliia.spring.model.Product;
import com.nataliia.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Product>> getAll() {
        return Optional.ofNullable(productDao.findAllProducts());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getById(Long id) {
        return Optional.ofNullable(productDao.findById(id));
    }

    @Transactional
    @Override
    public void add(Product product) {
        productDao.save(product);
    }

    @Transactional
    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}
