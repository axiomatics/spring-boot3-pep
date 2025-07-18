package com.axiomatics.demo.app.controller;

import com.axiomatics.springboot.annotation.AuthorizeWithAxiomaticsPDP;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@AuthorizeWithAxiomaticsPDP
public class HelloController {

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }
}
