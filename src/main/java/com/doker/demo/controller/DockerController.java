package com.doker.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

    @GetMapping("/docker-demo")
    public String dockerDemo(){
        return "<H1>DOCKER DEMO</H1>";
    }
}
