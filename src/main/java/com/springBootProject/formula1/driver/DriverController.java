package com.springBootProject.formula1.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/drivers")
public class DriverController {

    private final DriverService  driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getDrivers(){
        return driverService.getDrivers();
    }

    @PostMapping
    public void addDriver(@RequestBody Driver driver) { driverService.addNewDriver(driver); }

    @DeleteMapping(path = "{driverId}")
    public void deleteDriver (
            @PathVariable("driverId") Long driverId) {
        driverService.deleteDriver(driverId);
    }

    @PutMapping(path = "{driverId}")
    public void updateDriver(
            @PathVariable("driverId") Long driverId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String team) {
        driverService.updateDriver(driverId,name,team);
    }

}
