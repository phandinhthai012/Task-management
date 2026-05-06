package com.api.taskmanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

        @GetMapping("/healthCheck")
        public String test() {
            System.out.println("TEST CONTROLLER CALLED");
            return "API is working!";
        }
}
