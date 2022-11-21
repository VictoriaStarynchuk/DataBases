package com.iot.view;

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
