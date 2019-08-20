package com.nataliia.spring.service;

import com.nataliia.spring.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<List<Product>> getAll();

    Optional<Product> getById(Long id);

    void add(Product product);

    void update(Product product);

    void deleteById(Long id);
}
