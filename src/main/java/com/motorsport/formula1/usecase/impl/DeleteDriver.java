package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.ResponseHandler;
import com.motorsport.formula1.usecase.IDeleteDriver;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class DeleteDriver implements IDeleteDriver {
  private final DriverRepository driverRepository;

  @Override
  public ResponseEntity<Object> execute(Long id) {
    try {
      Optional<Driver> driver = driverRepository.findById(id);
      if (driver.isEmpty()) {
        return ResponseHandler.generate(
            "Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
      }
      driverRepository.deleteById(id);
      return ResponseHandler.generate("Driver deleted successfully", HttpStatus.OK, driver.get());
    } catch (Exception e) {
      log.error("Error deleting driver: ", e);
      return ResponseHandler.generate("Error deleting driver", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
