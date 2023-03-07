package com.springBootProject.formula1.service;

import com.springBootProject.formula1.domain.Driver;
import com.springBootProject.formula1.repository.DriverRepository;
import com.springBootProject.formula1.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public ResponseEntity<Object> get() {
        List<Driver> data = driverRepository.findAllByOrderById();
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results", HttpStatus.NOT_FOUND, data);
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        }
    }

    public ResponseEntity<Object> getByPosition(int position) {
        List<Driver> data = driverRepository.findByPositionOrderById(position);
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results for position " + position, HttpStatus.NOT_FOUND, data);
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        }
    }

    public ResponseEntity<Object> getByYearAndTeam(int year, String team) {
        List<Driver> data = driverRepository.findByYearAndTeamOrderById(year, team);
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results for year " + year + " and team " + team, HttpStatus.NOT_FOUND, data);
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        }
    }

    public ResponseEntity<Object> getByYear(int year) {
        List<Driver> data = driverRepository.findByYearOrderById(year);
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results for year " + year, HttpStatus.NOT_FOUND, data);
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        }
    }

    public ResponseEntity<Object> getByTeam(String team) {
        List<Driver> data = driverRepository.findByTeamOrderById(team);
        if (data.isEmpty()) {
            return ResponseHandler.generateResponse("No results for team " + team, HttpStatus.NOT_FOUND, data);
        } else {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        }
    }

    public ResponseEntity<Object> add(List<Driver> drivers) {
        List<Driver> existingDrivers = new ArrayList<>(Collections.emptyList());
        // Check input data for existing drivers
        for (Driver driver : drivers) {
            if (driverRepository.existsByNameAndYearAndTeam(
                    driver.getName(),
                    driver.getYear(),
                    driver.getTeam())) {
                existingDrivers.add(driver);
            }
        }
        if(existingDrivers.isEmpty()) {
            // Input validation OK, add drivers
            for (Driver driver : drivers) {
                driverRepository.save(driver);
            }
            return ResponseHandler.generateResponse("Drivers created successfully", HttpStatus.CREATED, drivers);
        } else {
            return ResponseHandler.generateResponse("Existing drivers detected", HttpStatus.CONFLICT, existingDrivers);
        }
    }

    @Transactional
    public ResponseEntity<Object> update(Long id,
                                         Driver newDriver) {
        Driver driver = driverRepository.findById(id).orElse(new Driver());
        if(driver.getId() == null) {
            return ResponseHandler.generateResponse("Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
        } else {
            if (driverRepository.existsByNameAndYearAndTeam(
                    newDriver.getName(),
                    newDriver.getYear(),
                    newDriver.getTeam())) {
                return ResponseHandler.generateResponse("Driver already exists", HttpStatus.CONFLICT);
            }
            driver.setYear(newDriver.getYear());
            driver.setName(newDriver.getName());
            driver.setTeam(newDriver.getTeam());
            return ResponseHandler.generateResponse("Driver updated successfully", HttpStatus.OK, driver);
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if(driver.isEmpty()) {
            return ResponseHandler.generateResponse("Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
        } else {
            driverRepository.deleteById(id);
            return ResponseHandler.generateResponse("Driver deleted successfully", HttpStatus.OK, driver);
        }
    }
}
