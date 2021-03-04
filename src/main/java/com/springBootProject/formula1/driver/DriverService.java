package com.springBootProject.formula1.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public void addNewDriver(Driver driver) {
        Optional<Driver> driverOptional = driverRepository.findByName(driver.getName());
        if (driverOptional.isPresent()){
            throw new IllegalStateException("Name taken");
        }
        driverRepository.save(driver);
    }

    public void deleteDriver(Long driverId) {
        boolean driverExists = driverRepository.existsById(driverId);
        if(!driverExists){
            throw new IllegalStateException(
                    "Driver with ID: " + driverId + " does not exist.");
        }
        driverRepository.deleteById(driverId);
    }

    @Transactional
    public void updateDriver(Long driverId,
                             String name,
                             String team) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalStateException(
                        "Driver with ID: " + driverId + " does not exist"
                ));
        if(     name != null &&
                name.length() > 0 &&
                !Objects.equals(driver.getName(),name)){
            if(driverRepository.existsByName(name)){
                throw new IllegalStateException("Name taken");
            }
            driver.setName(name);
        }

        if(     team != null &&
                team.length() > 0 &&
                !Objects.equals(driver.getTeam(),team)){
            if(driverRepository.countByTeam(team) >= 2){
                throw new IllegalStateException("Teams cannot have more than 2 drivers");
            }
            driver.setTeam(team);
        }
    }
}
