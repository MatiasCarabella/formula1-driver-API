package com.springBootProject.formula1.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v2/drivers")
public class DriverController {

    private final DriverService  driverService;

    @Autowired
    public DriverController(DriverService driverService) { this.driverService = driverService; }

    @GetMapping(path = "/get")
    public ResponseEntity<Object> getDriversByYear(@RequestParam Optional<Integer> year,
                                                   @RequestParam Optional<String> team) {
        if (year.isPresent() && team.isPresent()) {
            return driverService.getByYearAndTeam(year.orElse(0), team.orElse(""));
        } else if (year.isPresent()) {
            return driverService.getByYear(year.orElse(0));
        } else if (team.isPresent()) {
            return driverService.getByTeam(team.orElse(""));
        } else {
            return driverService.get();
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addDriver(@RequestBody List<Driver> driver){ return driverService.add(driver); }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Object> updateDriver(
            @PathVariable("id") Long id,
            @RequestBody Driver driver) {
        return driverService.update(id, driver);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteDriver (@PathVariable("id") Long id) { return driverService.delete(id); }

}
