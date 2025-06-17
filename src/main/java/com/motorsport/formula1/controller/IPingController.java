package com.motorsport.formula1.controller;

import org.springframework.http.ResponseEntity;

/** IPingController interface. */
public interface IPingController {

  /**
   * Check the service status.
   *
   * @return ResponseEntity with a simple status message indicating the service is running.
   */
  ResponseEntity<Object> ping();
}
