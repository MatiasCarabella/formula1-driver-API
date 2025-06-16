package com.motorsport.formula1.config;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.service.DriverService;
import com.motorsport.formula1.usecase.impl.GetDriversFromJson;
import com.motorsport.formula1.usecase.impl.IsDatabasePopulated;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class DataInitializer {

  private final DriverService driverService;
  private final GetDriversFromJson getDriversFromJson;
  private final IsDatabasePopulated isDatabasePopulated;

  public void loadDrivers() {
    if (isDatabasePopulated.execute()) {
      log.info("Drivers already exist in the database. Skipping initialization.");
      return;
    }
    loadDriversFromJson();
  }

  private void loadDriversFromJson() {
    try {
      List<Driver> drivers = getDriversFromJson.execute();
      driverService.add(drivers);
      log.info("Successfully loaded {} drivers into the database", drivers.size());
    } catch (IOException e) {
      log.error("Failed to load drivers from JSON: {}", e.getMessage(), e);
      throw new RuntimeException("Data initialization failed", e);
    }
  }
}
