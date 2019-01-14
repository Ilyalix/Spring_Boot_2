package com.project.sweater.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table (name = "user")
public class User implements UserDetails { // имплементируем UserDetails( UserDetails предоставляет необходимую информацию для построения объекта Authentication
                                           // именно его возвращает созданный нами сервис
    @Id // создаем поле ID
    @GeneratedValue(strategy = GenerationType.AUTO)// ; // автоматическая генерация
    private Long id;
    private String username;
    private String password;
    @Transient
    private String password2;
    private boolean active;

    // создание доп. таблицы автоматич. для хранения Enam (способ fetch = FetchType.EAGER (как данные будут подгружаться!),
    // то есть мало данных в таблице, и подгружаем всех сразу -> EAGER)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    // создаем таблицу user_role (для roles), которая соединяется с user_id (в roles)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)     // хранение Enum в виде строки
    private Set<Role> roles; // некий сэт ролей, который мы создаем в Enum


    public boolean isAdmin(){
        return roles.contains(Role.ADMIN); // возвращаем в списке ролей - роль админа
    }

    public boolean isUser(){
        return roles.contains(Role.USER); // возвращаем в списке ролей - роль user
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // права, роли пользователей
        return getRoles();
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}

