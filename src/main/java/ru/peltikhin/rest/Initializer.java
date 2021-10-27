package ru.peltikhin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.peltikhin.rest.security.AuthUser;
import ru.peltikhin.rest.security.AuthUserRepository;
import ru.peltikhin.rest.security.Role;

@Component
public class Initializer {
    @Autowired
    private AuthUserRepository authUserRepo;

    public void initial(){
        authUserRepo.deleteAll();
        AuthUser admin = new AuthUser(true, "admin", "1234", Role.SUPER_USER);
        AuthUser user = new AuthUser(true, "user", "1234",Role.USER);
        authUserRepo.save(user);
        authUserRepo.save(admin);
    }
}
