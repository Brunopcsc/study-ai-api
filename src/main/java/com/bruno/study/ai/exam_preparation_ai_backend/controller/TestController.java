package com.bruno.study.ai.exam_preparation_ai_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong! O servidor está vivo.";
    }
}