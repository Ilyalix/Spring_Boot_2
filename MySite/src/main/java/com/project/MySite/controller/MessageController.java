package com.project.MySite.controller;

import com.project.MySite.UserRepository.MessRepository;
import com.project.MySite.UserRepository.UserRepository;
import com.project.MySite.domain.Message;
import com.project.MySite.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessRepository messRepository;

    // userRepository создает экземпляр бина,

    @GetMapping("/home")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> mess;

        if (filter != null && !filter.isEmpty()) {
            mess = messRepository.findByTag(filter);
        } else {
            mess = messRepository.findAll();
        }

        model.addAttribute("messages", mess);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/home")
    public String add(@AuthenticationPrincipal User user, // добавляем объект User
                      @RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;

        if ((text != null && !text.isEmpty()) && (tag != null && !tag.isEmpty())) {
            Message mess = new Message(text, tag, user);
            messRepository.save(mess);
            messages = messRepository.findAll();
        } else {
            messages = messRepository.findAll();
        }
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("delete")
    @Transactional
    public String delete(@RequestParam(required = false, defaultValue = "") String delete, Map<String, Object> model) {
        Iterable<Message> messages;

        if (delete != null && !delete.isEmpty()) {
            messages = messRepository.deleteByText(delete);
        } else {
            messages = messRepository.findAll();
        }

        Iterable<Message> mes = messRepository.findAll();
        model.put("messages", mes);

        return "main";
    }

    @PostMapping("/home/{id}")
    @Transactional
    public String delete(@PathVariable Long id, Model model) {
        Iterable<Message> mess;

        messRepository.deleteById(id);
        mess = messRepository.findAll();

        model.addAttribute("messages", mess);

        return "redirect:/home";
    }
}




















// @RequestParam выдергивает значение либо из post запроса (форма в main), либо get запрос из url как пример с greeting
// RequestParam - парметр строки запроса, сравнивает значение с параметром метода greeting,
// он не является обязательным(required), если его нет то используется World.