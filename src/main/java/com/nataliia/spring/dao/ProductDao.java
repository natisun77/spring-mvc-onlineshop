package com.nataliia.spring.dao;

import com.nataliia.spring.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAllProducts();

    Product findById(long id);

    void save(Product product);

    void update(Product product);

    void deleteById(Long id);
}
