package com.iot.lab4.service.impl;

import com.iot.lab4.dao.PanelDao;
import com.iot.lab4.domain.Panel;
import com.iot.lab4.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanelServiceImpl implements PanelService {
    @Autowired
    private PanelDao panelDao;

    @Override
    public List<Panel> findAll() {
        return panelDao.findAll();
    }

    @Override
    public Optional<Panel> findById(Integer id) {
        return panelDao.findById(id);
    }

    @Override
    public int create(Panel panel) {
        return panelDao.create(panel);
    }

    @Override
    public int update(Integer id, Panel panel) {
        return panelDao.update(id, panel);
    }

    @Override
    public int delete(Integer id) {
        return panelDao.delete(id);
    }
}
