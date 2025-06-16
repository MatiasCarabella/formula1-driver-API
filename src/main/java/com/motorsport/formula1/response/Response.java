package com.motorsport.formula1.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
  private static final String MESSAGE_KEY = "message";
  private static final String STATUS_KEY = "status";
  private static final String DATA_KEY = "data";

  public static ResponseEntity<Object> generate(String message, HttpStatus status, Object... data) {
    Map<String, Object> body = new HashMap<>();
    body.put(MESSAGE_KEY, message);
    body.put(STATUS_KEY, status.value());
    if (data.length > 0 && data[0] != null) {
      body.put(DATA_KEY, data[0]);
    }
    return new ResponseEntity<>(body, status);
  }
}
