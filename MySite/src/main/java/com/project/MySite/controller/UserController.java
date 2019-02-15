package com.project.MySite.controller;
import com.project.MySite.Service.UserService;
import com.project.MySite.UserRepository.UserRepository;
import com.project.MySite.domain.Role;
import com.project.MySite.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user") // обычно на уровне методов, если на уровне класса, то мэппинг применим для всех методов

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')") // аннотация проверка на админ
    @GetMapping // по умолчанию мэппинг /user
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}") // по мимо главного мэппинга у класса /user ожидаем еще доп параметры (id)
    public String EditUser(@PathVariable User user, Model model) { // ожидаем на вход объект User и модель
        model.addAttribute("user", user); // заполняем модель
        model.addAttribute("roles", Role.values());
        return "userEditor";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(@RequestParam String username,
                           @RequestParam Map<String, String> form, // передаем на вход поля (user,admin) и прочее типо is и токен
                           @RequestParam("userId") User user // получаем пользователя
    ) {
        user.setUsername(username); // устанавливаем user новое имя

        // запрашиваем роли(Role.values()),переводим ENUM в строковый вид и сохраняем как roles:
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

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

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String passuser,
                                @RequestParam String pass1,
                                @RequestParam String pass2,
                                Model model) {

        if (passuser.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("message", "You must complete all fields!");
            return "profile";
        }

        if (passuser.length() < 3 || pass1.length() < 3 || pass2.length() < 3) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("message", "Your password must contain more than three characters!");
            return "profile";
        }

        if (!pass1.equals(pass2)) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("message", "Passwords are different");
            return "profile";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String dbPassword = user.getPassword(); // Load hashed DB password
        boolean pass = passwordEncoder.matches(passuser, dbPassword);

        if (!pass){
            model.addAttribute("username", user.getUsername());
            model.addAttribute("message", "Old password is not correct");
        }

        if (pass && (pass1.equals(pass2))) {
            if (passuser.equals(pass1)) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("message", "Old password and new password is equal");
            }else{
                user.setPassword(passwordEncoder.encode(pass1));
                userRepository.save(user);
                model.addAttribute("username", user.getUsername());
                model.addAttribute("new_message", "Password has been changed");
            }
        }
        return "profile";
    }
}
