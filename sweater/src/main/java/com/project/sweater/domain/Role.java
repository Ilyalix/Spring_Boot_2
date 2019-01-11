package com.project.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name(); // строковое представление USER
    }
}


// GrantedAuthority отражает разрешения, выданные пользователю в масштабе всего приложения,
// такие разрешения (как правило называются «роли»), например ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN.

