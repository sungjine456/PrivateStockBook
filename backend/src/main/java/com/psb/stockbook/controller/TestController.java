package com.psb.stockbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("in test controller");
        return "테스트 하이";
    }
}
