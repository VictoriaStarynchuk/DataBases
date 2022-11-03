package com.iot.lab4.service.impl;

import com.iot.lab4.dao.ElementDao;
import com.iot.lab4.domain.Element;
import com.iot.lab4.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementServiceImpl implements ElementService {
    @Autowired
    private ElementDao elementDao;

    @Override
    public List<Element> findAll() {
        return elementDao.findAll();
    }

    @Override
    public Optional<Element> findById(Integer id) {
        return elementDao.findById(id);
    }

    @Override
    public int create(Element element) {
        return elementDao.create(element);
    }

    @Override
    public int update(Integer id, Element element) {
        return elementDao.update(id, element);
    }

    @Override
    public int delete(Integer id) {
        return elementDao.delete(id);
    }
}
