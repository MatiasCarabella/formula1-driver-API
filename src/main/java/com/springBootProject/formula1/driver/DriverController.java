package com.springBootProject.formula1.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/drivers")
public class DriverController {

    private final DriverService  driverService;

    @Autowired
    public DriverController(DriverService driverService) { this.driverService = driverService; }

    @GetMapping(path = "/get/year/{year}")
    public ResponseEntity<Object> getDriversByYear(@PathVariable("year") int year) { return driverService.getByYear(year); }

    @GetMapping(path = "/get/team/{team}")
    public ResponseEntity<Object> getDriversByTeam(@PathVariable("team") String team) { return driverService.getByTeam(team); }

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
