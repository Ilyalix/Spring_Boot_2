package com.project.sweater.controller;

import com.project.sweater.UserRepository.MessRepository;
import com.project.sweater.domain.Message;
import com.project.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessRepository messRepository;
    // userRepository создает экземпляр бина,
    // он учитывает соответствующий интерфейс и передает ссылку на соответствующий ресурс.

    @GetMapping ("/home")// обрабатываем просто get запросы в URL
    public String main (@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> mess; // найти все из userRepository и поменстить в mess

        if (filter != null && !filter.isEmpty()) {
            mess = messRepository.findByTag(filter);
        } else {
            mess = messRepository.findAll();
        }

        model.addAttribute("messages", mess);
        model.addAttribute("filter", filter); // передаем фильтр в форму в main

        return "main";
    }

    @PostMapping("/home") // обрабатываем post запросы в форме в html
    public String add (
            @AuthenticationPrincipal User user, // добавляем объект User
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messsg;

        // сохраняем
        if ((text != null && !text.isEmpty()) && (tag != null && !tag.isEmpty())) {
            Message mess = new Message(text, tag, user);
            messRepository.save(mess);
            messsg = messRepository.findAll();
        } else {
           messsg = messRepository.findAll();// найти все из userRepository и поместить в mess (alt-ctrl-V)
        }
           model.put("messages", messsg);

           return "main";
        }

    @Transactional
    @PostMapping("delete")
    public String delete(@RequestParam String delete, Map<String, Object> model) {
        Iterable<Message> messages;

        if (delete != null && !delete.isEmpty()) {
            messages = messRepository.deleteByTag(delete);
        } else {
            messages = messRepository.findAll();
        }

        Iterable<Message> mes = messRepository.findAll();
        model.put("messages", mes);

        return "main";
    }
}


// @RequestParam выдергивает значение либо из post запроса (форма в main), либо get запрос из url как пример с greeting
// RequestParam - парметр строки запроса, сравнивает значение с параметром метода greeting,
// он не является обязательным(required), если его нет то используется World.