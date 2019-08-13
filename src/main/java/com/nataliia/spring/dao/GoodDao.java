package com.nataliia.spring.dao;

import com.nataliia.spring.model.Good;

import java.util.List;

public interface GoodDao {

    List<Good> findAllGoods();

    Good findById(long id);

    void save(Good good);

    void update(Good good);

    void deleteById(Long id);
}
