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

    @GetMapping(path = "/get")
    public List<Driver> getDrivers(){
        return driverService.getDrivers();
    }

    @PostMapping(path = "/add")
    public void addDriver(@RequestBody Driver driver){
        driverService.addDriver(driver);
    }

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
