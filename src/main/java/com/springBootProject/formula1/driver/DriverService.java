package com.springBootProject.formula1.driver;

import com.springBootProject.formula1.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Object> getDriversByYear(int year) {
        List<Driver> data = driverRepository.findByYear(year);
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results", HttpStatus.NOT_FOUND, driverRepository.findByYear(year));
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, driverRepository.findByYear(year));
        }
    }

    public List<Driver> getDriversByTeam(String team) {
        return driverRepository.findByTeam(team);
    }

    public ResponseEntity<Object> addDriver(Driver driver) {
        Optional<Driver> driverOptional = driverRepository.findByName(driver.getName());
        if (driverOptional.isPresent()){
            return ResponseHandler.generateResponse("User already exists", HttpStatus.CONFLICT);
        } else {
            Driver newDriver = driverRepository.save(driver);
            return ResponseHandler.generateResponse("User created successfully!", HttpStatus.CREATED, newDriver);
        }
    }

    @Transactional
    public void updateDriver(Long driverId,
                             Driver newDriver) {
        Driver oldDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalStateException("Driver with ID: " + driverId + " does not exist"));

        if(     newDriver.getName() != null &&
                newDriver.getName().length() > 0 &&
                !Objects.equals(oldDriver.getName(),newDriver.getName())){
            if(driverRepository.existsByName(newDriver.getName())){
                throw new IllegalStateException("Name taken");
            }
            oldDriver.setName(newDriver.getName());
        }
        if(     newDriver.getTeam() != null &&
                newDriver.getTeam().length() > 0 &&
                !Objects.equals(oldDriver.getTeam(),newDriver.getTeam())){
            if(driverRepository.countByTeam(newDriver.getTeam()) >= 2){
                throw new IllegalStateException("Teams cannot have more than 2 drivers");
            }
            oldDriver.setTeam(newDriver.getTeam());
        }
    }

    public void deleteDriver(Long driverId) {
        boolean driverExists = driverRepository.existsById(driverId);
        if(!driverExists){
            throw new IllegalStateException("Driver with ID: " + driverId + " does not exist.");
        }
        driverRepository.deleteById(driverId);
    }
}
