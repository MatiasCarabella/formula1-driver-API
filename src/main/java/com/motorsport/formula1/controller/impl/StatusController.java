package com.motorsport.formula1.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class StatusController implements IStatusController {

  // Check the service status
  @GetMapping
  public ResponseEntity<Object> checkStatus() {
    Map<String, String> status = new HashMap<>();
    status.put("status", "Ready to go! 🚦🏁");
    return ResponseEntity.ok().body(status);
  }
}
