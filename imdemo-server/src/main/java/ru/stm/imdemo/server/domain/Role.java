package ru.stm.imdemo.server.domain;
/**
 * Здесь нам сообщают роль пользователя, с помощью enum
 */
import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
