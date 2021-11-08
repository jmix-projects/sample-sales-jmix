package com.company.samplesales.rest;

import io.jmix.core.security.CurrentAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserRestController {

    protected final CurrentAuthentication currentAuthentication;

    public UserRestController(CurrentAuthentication currentAuthentication) {
        this.currentAuthentication = currentAuthentication;
    }

    @GetMapping("/userInfo")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok(currentAuthentication.getUser().getUsername());
    }
}
