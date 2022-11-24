package com.iot.service.impl;

import com.iot.domain.PanelEntity;
import com.iot.exception.PanelNotFoundException;
import com.iot.repository.PanelEntityRepository;
import com.iot.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PanelServiceImpl implements PanelService {
    @Autowired
    PanelEntityRepository panelEntityRepository;

    public List<PanelEntity> findAll() {
        return panelEntityRepository.findAll();
    }

    public PanelEntity findById(Integer id) {
        return panelEntityRepository.findById(id)
                .orElseThrow(()-> new PanelNotFoundException(id));
    }

    @Transactional
    public PanelEntity create(PanelEntity panel) {
        panelEntityRepository.save(panel);
        return panel;
    }
    @Transactional
    public void update(Integer id, PanelEntity panel) {
        PanelEntity panelEntity = panelEntityRepository.findById(id)
                .orElseThrow(()-> new PanelNotFoundException(id));
        panelEntity.setType(panel.getType());
        panelEntity.setPower(panel.getPower());
        panelEntity.setElements(panel.getElements());
        panelEntity.setDurationTime(panel.getDurationTime());
        panelEntity.setProductionPower(panel.getProductionPower());
        panelEntity.setTiltAngel(panel.getTiltAngel());
        panelEntityRepository.save(panelEntity);
    }
    @Transactional
    public void delete(Integer id) {
        PanelEntity panelEntity = panelEntityRepository.findById(id)
                .orElseThrow(()-> new PanelNotFoundException(id));
        panelEntityRepository.delete(panelEntity);
    }

    @Override
    public BigDecimal getAvgPower() {
        return panelEntityRepository.getAvgPower();
    }
}
