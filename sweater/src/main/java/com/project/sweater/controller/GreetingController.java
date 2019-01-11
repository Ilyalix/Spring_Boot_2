package com.project.sweater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

//    @GetMapping("/greeting") // аннотация
//    public String greeting(
//            @RequestParam(name="names", required=false, defaultValue="World")
//            String name, Map<String,Object> model)
//    {
//        model.put("names", name);
//        return "greeting";
//    }

    @GetMapping("/") // аннотация
    public String greeting(Model model) {
          return "greeting";
    }

//    @GetMapping("/Hi") // аннотация
//    public String hi (Map<String, Object> model){
//        model.put("some", "World");
//        return "greeting";
//    }
}

// @RequestParam выдергивает значение либо из post запроса (форма в main), либо get запрос из url как пример с greeting

// RequestParam - парметр строки запроса, сравнивает значение с параметром метода greeting,
// он не является обязательным(required), если его нет то используется World.