package com.motorsport.formula1.usecase;

import com.motorsport.formula1.domain.Driver;
import org.springframework.http.ResponseEntity;

/** IUpdateDriver interface. */
@FunctionalInterface
public interface IUpdateDriver {
  /**
   * Update an existing driver's information.
   *
   * @param id ID of the driver to update
   * @param newDriver Updated driver information
   * @return ResponseEntity indicating success or failure
   */
  ResponseEntity<Object> execute(Long id, Driver driver);
}
