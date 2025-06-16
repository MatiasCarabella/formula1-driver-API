package com.motorsport.formula1.usecase;

import org.springframework.http.ResponseEntity;

/** IDeleteDriver interface. */
@FunctionalInterface
public interface IDeleteDriver {
  /**
   * Deletes a driver by their ID.
   *
   * @param id the ID of the driver to delete
   * @return ResponseEntity indicating success or failure
   */
  ResponseEntity<Object> execute(Long id);
}
