package com.example.demo.pojo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!!";
    }
}
