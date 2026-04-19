package com.security.auth.userController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping("/")
    public String hello(){
        return "Hey Niku baby";
    }

    @GetMapping("/hi")
    public String hi(){
        return "Hey";
    }
}
