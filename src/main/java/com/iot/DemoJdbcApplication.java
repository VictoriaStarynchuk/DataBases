package com.iot;

import com.iot.view.MyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJdbcApplication implements CommandLineRunner {

    @Autowired
    private MyView view;

    public static void main(String[] args) {
        SpringApplication.run(DemoJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        view.show();
    }
}
