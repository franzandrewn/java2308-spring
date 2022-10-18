package com.andrewn.java2305spring.controller;

import com.andrewn.java2305spring.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "Hello world!";
    }

    @GetMapping("/hellon")
    String hellon(@RequestParam(name="n", required=false, defaultValue="1") String n) {
        String result = "";
        for (int i = 0; i < Integer.parseInt(n); i++) {
            result += "hello ";
        }
        return result + " world";
    }

    @GetMapping("/create-client")
    List<Client> createClient(@RequestParam(name="login") String login, @RequestParam(name="pass") String password) {
        List<Client> res = new LinkedList<>();
        res.add(new Client(login, password));
        res.add(new Client(login + "_hidden", password));
        return res;
    }
}
