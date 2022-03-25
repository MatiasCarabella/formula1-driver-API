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
    public List<Driver> getDriversByYear(@PathVariable("year") int year) { return driverService.getDriversByYear(year); }

    @GetMapping(path = "/get/team/{team}")
    public List<Driver> getDriversByTeam(@PathVariable("team") String team) { return driverService.getDriversByTeam(team); }

    @PostMapping(path = "/add")
    public ResponseEntity addDriver(@RequestBody Driver driver){ return driverService.addDriver(driver); }

    @PutMapping(path = "/update/{driverId}")
    public void updateDriver(
            @PathVariable("driverId") Long driverId,
            @RequestBody Driver driver) {
        driverService.updateDriver(driverId, driver);
    }

    @DeleteMapping(path = "/delete/{driverId}")
    public void deleteDriver (@PathVariable("driverId") Long driverId) {
        driverService.deleteDriver(driverId);
    }

}
