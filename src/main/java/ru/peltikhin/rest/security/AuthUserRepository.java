package ru.peltikhin.rest.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String aString);
}
