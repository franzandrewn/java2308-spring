package com.andrewn.java2305spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TempControllers {

    @GetMapping("/template-hello")
    String templateHello() {
        return "tempHello";
    }

    @GetMapping("/hello-for-name")
    String helloForName(@RequestParam(name="fullname", required = false, defaultValue = "world") String fullname,
                        Model model) {
        model.addAttribute("username", fullname);
        return "helloWithName";
    }
}
