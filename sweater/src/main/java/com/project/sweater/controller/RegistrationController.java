package com.project.sweater.controller;

import com.project.sweater.UserRepository.UserRepository;
import com.project.sweater.domain.Role;
import com.project.sweater.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

// возвращаем view
    @GetMapping("/registration") // get запрос, просто возращает view
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration") // post запрос
    public String addUser(User user, Map<String,Object> model){ // поступает на вход объект User
       User UsFromDb = userRepository.findByUsername(user.getUsername()); // найти пользователя

            if(UsFromDb != null) {
                model.put("message", "User exists!");
                return "registration";
           }
            if(user.getUsername().isEmpty()) {
                model.put("message", "User is empty!");
                return "registration";
            }

            if(user.getUsername().length()< 3 || user.getPassword().length()< 3) {
            model.put("message", "login or password is short!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); // создание set-a с одним единственным значением USER
        user.setPassword(passwordEncoder.encode(user.getPassword())); // устанавливаем шифров. паролей
        userRepository.save(user);

        return "redirect:/login"; // при успешной авториз. возвращаемся на страницу login

    }
}
