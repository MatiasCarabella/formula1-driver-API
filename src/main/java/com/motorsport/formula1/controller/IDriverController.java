package com.motorsport.formula1.controller;

import com.motorsport.formula1.entity.Driver;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/** IDriverController interface. */
public interface IDriverController {

  /**
   * Get drivers with optional filters.
   *
   * @param driver
   * @param team
   * @param position
   * @param year
   * @return ResponseEntity with a list of drivers or an error message.
   */
  ResponseEntity<Object> getDrivers(
      @RequestParam(required = false) Optional<String> driver,
      @RequestParam(required = false) Optional<String> team,
      @RequestParam(required = false) Optional<Integer> position,
      @RequestParam(required = false) Optional<Integer> year);

  /**
   * Add new drivers to the database.
   *
   * @param drivers
   * @return ResponseEntity with the result of the operation.
   */
  ResponseEntity<Object> addDrivers(@RequestBody List<Driver> drivers);

  /**
   * Update an existing driver.
   *
   * @param id
   * @param driver
   * @return ResponseEntity with the result of the operation.
   */
  ResponseEntity<Object> updateDriver(@PathVariable Long id, @RequestBody Driver driver);

  /**
   * Delete a driver by ID.
   *
   * @param id
   * @return ResponseEntity with the result of the operation.
   */
  ResponseEntity<Object> deleteDriver(@PathVariable Long id);

  /**
   * Initialize the database with default data.
   *
   * @return ResponseEntity with the result of the initialization.
   */
  ResponseEntity<Object> initializeDatabase();
}
