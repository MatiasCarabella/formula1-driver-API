package com.motorsport.formula1.controller;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.usecase.ICreateDrivers;
import com.motorsport.formula1.usecase.IDeleteDriver;
import com.motorsport.formula1.usecase.IGetDriversWithFilters;
import com.motorsport.formula1.usecase.IInitializeDatabase;
import com.motorsport.formula1.usecase.IUpdateDriver;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/drivers")
@Validated
@AllArgsConstructor
public class DriverController {

  private final ICreateDrivers createDrivers;
  private final IGetDriversWithFilters getDriversWithFilters;
  private final IUpdateDriver updateDriver;
  private final IDeleteDriver deleteDriver;
  private final IInitializeDatabase initializeDatabase;

  // Fetch a list of drivers with optional filters
  @GetMapping
  public ResponseEntity<Object> getDrivers(
      @RequestParam(required = false) Optional<String> driver,
      @RequestParam(required = false) Optional<String> team,
      @RequestParam(required = false) Optional<Integer> position,
      @RequestParam(required = false) Optional<Integer> year) {
    return getDriversWithFilters.execute(driver, team, position, year);
  }

  // Add new drivers to the database
  @PostMapping
  public ResponseEntity<Object> addDrivers(@RequestBody List<Driver> drivers) {
    return createDrivers.execute(drivers);
  }

  // Update an existing driver based on their ID
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
    return updateDriver.execute(id, driver);
  }

  // Delete a driver from the database by their ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteDriver(@PathVariable Long id) {
    return deleteDriver.execute(id);
  }

  // Initialize the database with some pre-defined driver data
  @PostMapping("/initialize")
  public ResponseEntity<Object> initializeData() {
    return initializeDatabase.execute();
  }
}
