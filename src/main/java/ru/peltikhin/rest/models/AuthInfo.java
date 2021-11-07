package ru.peltikhin.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peltikhin.rest.security.AuthUser;
import ru.peltikhin.rest.security.Role;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AuthInfo {
    private Optional<Long> id;
    private Optional<String> username;
    private Optional<Role> role;

    public AuthInfo(AuthUser user){
        id = Optional.of(user.getId());
        username = Optional.of(user.getUsername());
        role = Optional.of(user.getRole());
    }
}
