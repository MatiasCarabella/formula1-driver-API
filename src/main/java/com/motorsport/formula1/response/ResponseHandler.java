package com.motorsport.formula1.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

  public static ResponseEntity<Object> generateResponse(
      String message, HttpStatus status, Object responseObject) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", message);
    map.put("status", status.value());
    if (responseObject != null) {
      map.put("data", responseObject);
    }
    return new ResponseEntity<>(map, status);
  }

  public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
    return generateResponse(message, status, null);
  }
}
