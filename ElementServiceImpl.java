package com.iot.service.impl;

import com.iot.domain.ElementEntity;
import com.iot.exception.ElementNotFoundException;
import com.iot.repository.ElementEntityRepository;
import com.iot.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {
    @Autowired
    ElementEntityRepository elementEntityRepository;

    public List<ElementEntity> findAll() {
        return elementEntityRepository.findAll();
    }

    public ElementEntity findById(Integer id) {
        return elementEntityRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id));
    }
    @Transactional
    public ElementEntity create(ElementEntity element) {
         elementEntityRepository.save(element);
        return element;
    }

    @Transactional
    public void update(Integer id, ElementEntity element) {
            ElementEntity elementEntity = elementEntityRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException(id));
            elementEntity.setBattery(element.getBattery());
            elementEntity.setPanel(element.getPanel());
            elementEntity.setStations(element.getStations());
            elementEntity.setBatteryQuantity(element.getBatteryQuantity());
            elementEntity.setPanelQuantity(element.getPanelQuantity());
            elementEntityRepository.save(elementEntity);
    }

    @Transactional
    public void delete(Integer id) {
        ElementEntity elementEntity = elementEntityRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id));
        elementEntityRepository.delete(elementEntity);
    }

    @Transactional
    public void manyToManyRel(String panelType, String batteryType) {
        elementEntityRepository.manyToManyRel(panelType, batteryType);
    }
}
