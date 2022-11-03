package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.ElementController;
import com.iot.lab4.domain.Element;
import com.iot.lab4.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementControllerImpl implements ElementController {
    @Autowired
    private ElementService elementService;

    @Override
    public List<Element> findAll() {
        return elementService.findAll();
    }

    @Override
    public Optional<Element> findById(Integer id) {
        return elementService.findById(id);
    }

    @Override
    public int create(Element element) {
        return elementService.create(element);
    }

    @Override
    public int update(Integer id, Element element) {
        return elementService.update(id, element);
    }

    @Override
    public int delete(Integer id) {
        return elementService.delete(id);
    }
}
