package com.springBootProject.formula1.controller;

import com.springBootProject.formula1.domain.Driver;
import com.springBootProject.formula1.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/drivers")
@Validated
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // Fetch a list of drivers with optional filters
    @GetMapping
    public ResponseEntity<Object> getDrivers(
            @RequestParam(required = false) Optional<String> driver,
            @RequestParam(required = false) Optional<String> team,
            @RequestParam(required = false) Optional<Integer> position,
            @RequestParam(required = false) Optional<Integer> year) {
        return driverService.get(driver, team, position, year);
    }

    // Add new drivers to the database
    @PostMapping
    public ResponseEntity<Object> addDrivers(@RequestBody List<Driver> drivers) {
        return driverService.add(drivers);
    }

    // Update an existing driver based on their ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDriver(
            @PathVariable Long id,
            @RequestBody Driver driver) {
        return driverService.update(id, driver);
    }

    // Delete a driver from the database by their ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDriver(@PathVariable Long id) {
        return driverService.delete(id);
    }

    // Initialize the database with some pre-defined driver data
    @PostMapping("/initialize")
    public ResponseEntity<Object> initializeData() {
        return driverService.initializeData();
    }
}
