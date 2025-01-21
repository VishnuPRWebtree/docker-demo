package com.doker.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DockerController {

    @Value("${application.demo}")
    private String demo;

    @GetMapping("/docker-demo")
    public String dockerDemo() {
        return "<H1>" + demo + "</H1>";
    }


}
