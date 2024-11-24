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
@RequestMapping("api/v2/drivers")
@Validated
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<Object> getDrivers(
            @RequestParam(required = false) Optional<String> driver,
            @RequestParam(required = false) Optional<String> team,
            @RequestParam(required = false) Optional<Integer> position,
            @RequestParam(required = false) Optional<Integer> year) {
        return driverService.get(driver, team, position, year);
    }

    @PostMapping
    public ResponseEntity<Object> addDrivers(@RequestBody List<Driver> drivers) {
        return driverService.add(drivers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDriver(
            @PathVariable Long id,
            @RequestBody Driver driver) {
        return driverService.update(id, driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDriver(@PathVariable Long id) {
        return driverService.delete(id);
    }

    // Initialize DB
    @PostMapping("/initialize")
    public ResponseEntity<Object> initializeData() {
        return driverService.initializeData();
    }
}
