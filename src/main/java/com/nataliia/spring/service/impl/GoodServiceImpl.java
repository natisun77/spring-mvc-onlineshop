package com.nataliia.spring.service.impl;

import com.nataliia.spring.dao.GoodDao;
import com.nataliia.spring.model.Good;
import com.nataliia.spring.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodDao goodDao;

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Good>> getAll() {
        return Optional.ofNullable(goodDao.findAllGoods());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Good> getById(Long id) {
        return Optional.ofNullable(goodDao.findById(id));
    }

    @Transactional
    @Override
    public void add(Good good) {
        goodDao.save(good);
    }

    @Transactional
    @Override
    public void update(Good good) {
        goodDao.update(good);
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        goodDao.deleteById(id);
    }
}
