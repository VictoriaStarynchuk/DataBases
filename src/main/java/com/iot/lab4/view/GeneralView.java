package com.iot.lab4.view;

import java.util.Map;

public interface GeneralView {
    Map<String, String> getMenu();
    Map<String, Printable> getMethodsMenu();
    void findAll();
    void findById();
    void create();
    void update();
    void delete();
}
