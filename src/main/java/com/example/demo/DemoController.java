package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @GetMapping(path = "/private/hello")
    public String test(Principal principal) {
        return principal.getName();
    }

    @GetMapping(path = "/public/hello")
    public String test2() {
        return "OK!";
    }

}
