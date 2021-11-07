package ru.peltikhin.rest.controllers;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peltikhin.rest.models.AuthInfo;
import ru.peltikhin.rest.security.AuthUser;

import java.util.Optional;

@RestController
public class MainController {

    @GetMapping(path= "/hello")
    String hello() {
        return "Hello";
    }

    @GetMapping(path= "/auth/this")
    AuthInfo getCurrentUser(){
        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == "anonymousUser"){
            return new AuthInfo(Optional.empty(), Optional.empty(), Optional.empty());
        }
        return new AuthInfo((AuthUser) user);
    }
}
