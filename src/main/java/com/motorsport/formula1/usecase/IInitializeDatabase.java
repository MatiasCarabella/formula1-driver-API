package com.motorsport.formula1.usecase;

import org.springframework.http.ResponseEntity;

/** IInitializeDatabase interface. */
@FunctionalInterface
public interface IInitializeDatabase {
  /**
   * Initializes the database with default data.
   *
   * @return ResponseEntity indicating success or failure
   */
  ResponseEntity<Object> execute();
}
