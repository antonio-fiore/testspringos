package com.example.testspringos.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld()
    {
        return "Hello World da OpenShift";
    }

    @PostMapping("/testpost")
    public Map<String,Object> postMethodName(@RequestBody Map<String,Object> payload) {

        if (!payload.containsKey("username"))
        {
            throw new IllegalArgumentException("username is required");
        }
        
        String username = (String) payload.get("username");

        return Map.of("result","ok","username",username);
        
    }
    
    
}
