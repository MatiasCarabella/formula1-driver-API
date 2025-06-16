package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.response.Response;
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
        return Response.generate(
            "Drivers already exist in the database. Skipping initialization.", HttpStatus.OK);
      }
      List<Driver> drivers = getDriversFromJson.execute();
      createDrivers.execute(drivers);
      return Response.generate("Successfully initialized driver data.", HttpStatus.OK);
    } catch (IOException e) {
      log.error("Failed to initialize data: ", e);
      return Response.generate(
          "Failed to initialize data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
