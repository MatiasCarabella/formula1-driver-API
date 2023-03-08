package com.springBootProject.formula1.controller;

import com.springBootProject.formula1.domain.Driver;
import com.springBootProject.formula1.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v2/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) { this.driverService = driverService; }

    @GetMapping(path = "/get")
    public ResponseEntity<Object> getDrivers(@RequestParam Optional<Integer> year,
                                             @RequestParam Optional<String> team,
                                             @RequestParam Optional<Integer> position) {
        return driverService.get(year, team, position);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addDriver(@RequestBody List<Driver> driver){ return driverService.add(driver); }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateDriver(
            @RequestParam Optional<Long> id,
            @RequestBody Driver driver) {
        return driverService.update(id.orElse(0L), driver);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteDriver (@RequestParam Optional<Long> id) { return driverService.delete(id.orElse(0L)); }

}
