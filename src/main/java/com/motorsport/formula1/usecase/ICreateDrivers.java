package com.motorsport.formula1.usecase;

import com.motorsport.formula1.domain.Driver;
import java.util.List;
import org.springframework.http.ResponseEntity;

/** ICreateDrivers interface. */
@FunctionalInterface
public interface ICreateDrivers {
  /**
   * Add a list of new drivers to the database.
   *
   * @param drivers List of drivers to add
   * @return ResponseEntity indicating success or failure
   */
  ResponseEntity<Object> execute(List<Driver> drivers);
}
