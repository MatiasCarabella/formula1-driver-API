package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.ResponseHandler;
import com.motorsport.formula1.usecase.IUpdateDriver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UpdateDriver implements IUpdateDriver {
  private final DriverRepository driverRepository;

  @Override
  public ResponseEntity<Object> execute(Long id, Driver driver) {
    try {
      return driverRepository
          .findById(id)
          .map(
              existingDriver -> {
                if (driverRepository.existsByNameAndYearAndTeam(
                    driver.getName(), driver.getYear(), driver.getTeam())) {
                  return ResponseHandler.generate("Driver already exists", HttpStatus.CONFLICT);
                }
                existingDriver.setYear(driver.getYear());
                existingDriver.setName(driver.getName());
                existingDriver.setTeam(driver.getTeam());
                final Driver updatedDriver = driverRepository.save(existingDriver);
                return ResponseHandler.generate(
                    "Driver updated successfully", HttpStatus.OK, updatedDriver);
              })
          .orElse(
              ResponseHandler.generate(
                  "Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      log.error("Error updating driver: ", e);
      return ResponseHandler.generate("Error updating driver", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
