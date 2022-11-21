package com.iot.controller.impl;

import com.iot.controller.PanelController;
import com.iot.domain.Panel;
import com.iot.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanelControllerImpl implements PanelController {
    @Autowired
    private PanelService panelService;

    @Override
    public List<Panel> findAll() {
        return panelService.findAll();
    }

    @Override
    public Optional<Panel> findById(Integer id) {
        return panelService.findById(id);
    }

    @Override
    public int create(Panel panel) {
        return panelService.create(panel);
    }

    @Override
    public int update(Integer id, Panel panel) {
        return panelService.update(id, panel);
    }

    @Override
    public int delete(Integer id) {
        return panelService.delete(id);
    }

}
