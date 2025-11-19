package com.motorsport.formula1.response;

import com.motorsport.formula1.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

  public static ResponseEntity<Object> generate(String message, HttpStatus status) {
    final Response response = Response.builder().message(message).status(status.value()).build();
    return new ResponseEntity<>(response, status);
  }

  public static ResponseEntity<Object> generate(String message, HttpStatus status, Object data) {
    final Response response =
        Response.builder().message(message).status(status.value()).data(data).build();
    return new ResponseEntity<>(response, status);
  }
}
