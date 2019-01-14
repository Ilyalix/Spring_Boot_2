package com.project.sweater.controller;

import com.project.sweater.UserRepository.UserRepository;
import com.project.sweater.domain.Role;
import com.project.sweater.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
    public String addUser(@Valid User user, Model model) { // поступает на вход объект User
      User UsFromDb = userRepository.findByUsername(user.getUsername()); // найти пользователя

        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getPassword2().isEmpty()) {
                model.addAttribute("message", "One field is empty!");
                return "registration";
        }

        if(UsFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        if (!user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("message", "Passwords are different!");
            return "registration";
        }

        if(user.getUsername().length()< 3 || user.getPassword().length()< 3 || user.getPassword2().length()< 3) {
            model.addAttribute("message", "login or password is short!");
            return "registration";
        }

        if (user.getPassword().equals(user.getPassword2())) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER)); // создание set-a с одним единственным значением USER
            user.setPassword(passwordEncoder.encode(user.getPassword())); // устанавливаем шифров. паролей
            userRepository.save(user);
        }

        model.addAttribute("mess", "Are you registerd!");
        return "login";
//        return "redirect:/login"; // при успешной авториз. возвращаемся на страницу login

    }
}
