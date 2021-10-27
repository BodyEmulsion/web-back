package ru.peltikhin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static Initializer initializer;

    @Autowired
    public void setInitialLoader(Initializer initializer) {
        Main.initializer = initializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        initializer.initial();
    }
}
