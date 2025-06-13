package com.motorsport.formula1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class StatusController {

    // Check the service status
    @GetMapping
    public ResponseEntity<Object> checkStatus() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "Ready to go! 🚦🏁");
        return ResponseEntity.ok().body(status);
    }
}
