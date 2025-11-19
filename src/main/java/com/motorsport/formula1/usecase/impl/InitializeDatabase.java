package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.response.ResponseHandler;
import com.motorsport.formula1.usecase.ICreateDrivers;
import com.motorsport.formula1.usecase.IGetDriversFromJson;
import com.motorsport.formula1.usecase.IInitializeDatabase;
import com.motorsport.formula1.usecase.IIsDatabasePopulated;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class InitializeDatabase implements IInitializeDatabase {
  private final IIsDatabasePopulated isDatabasePopulated;
  private final IGetDriversFromJson getDriversFromJson;
  private final ICreateDrivers createDrivers;

  @Override
  public ResponseEntity<Object> execute() {
    try {
      if (isDatabasePopulated.execute()) {
        return ResponseHandler.generate(
            "Drivers already exist in the database. Skipping initialization.", HttpStatus.CONFLICT);
      }
      final List<Driver> drivers = getDriversFromJson.execute();
      createDrivers.execute(drivers);
      return ResponseHandler.generate("Successfully initialized driver data.", HttpStatus.OK);
    } catch (IOException e) {
      log.error("Failed to initialize data: ", e);
      return ResponseHandler.generate(
          "Failed to initialize data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
