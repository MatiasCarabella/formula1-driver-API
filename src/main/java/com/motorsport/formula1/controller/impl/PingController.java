package com.motorsport.formula1.controller.impl;

import com.motorsport.formula1.controller.IPingController;
import com.motorsport.formula1.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class PingController implements IPingController {

  // Ping endpoint to check service status
  @GetMapping
  public ResponseEntity<Object> ping() {
    return Response.generate("Ready to go! 🚦🏁", HttpStatus.OK);
  }
}
