package com.project.sweater.controller;

import com.project.sweater.UserRepository.UserRepository;
import com.project.sweater.domain.Role;
import com.project.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") // обычно на уровне методов, если на уровне класса, то мэппинг применим для всех методов
@PreAuthorize("hasAuthority('ADMIN')") // аннотация проверка на админ
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping // по умолчанию мэппинг /user
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}") // по мимо главного мэппинга у класса /user ожидаем еще доп параметры (id)
    public String EditUser(@PathVariable User user, Model model){ // ожидаем на вход объект User и модель
        model.addAttribute("user", user); // заполняем модель
        model.addAttribute("roles", Role.values());
        return "userEditor";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form, // передаем на вход поля (user,admin) и прочее типо is и токен
            @RequestParam("userId") User user // получаем пользователя из БД
    ) {
        user.setUsername(username); // устанавливаем user новое имя


        // запрашиваем роли(Role.values()),переводим ENUM в строковый вид и сохраняем как roles:
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); // очищаем все роли

        // проверка, что данная форма содержим роли для данного пользователя
        for (String key : form.keySet()) { // получаем список ключей и пробегаемся по нему
            if (roles.contains(key)) { // берем roles,проверяем содержит ключ
                user.getRoles().add(Role.valueOf(key)); // и добавляем пользователю роль,которую получаем через valueOf(
            }
        }

        userRepository.save(user); // сохраняем

        return "redirect:/user";
    }
}
