package com.motorsport.formula1.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.service.DriverService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

@Configuration
@AllArgsConstructor
@Slf4j
public class DataInitializer {

  private static final String DRIVERS_JSON_PATH = "/data/drivers.json";

  private final ObjectMapper objectMapper;
  private final DriverService driverService;

  public void loadDrivers() {
    if (isDatabasePopulated()) {
      log.info("Drivers already exist in the database. Skipping initialization.");
      return;
    }
    loadDriversFromJson();
  }

  private boolean isDatabasePopulated() {
    try {
      List<Driver> existingDrivers = driverService.getAllDrivers();
      return !CollectionUtils.isEmpty(existingDrivers);
    } catch (Exception e) {
      log.error("Error checking database state: {}", e.getMessage(), e);
      return false;
    }
  }

  private void loadDriversFromJson() {
    try {
      List<Driver> drivers = readDriversFromJson();
      driverService.add(drivers);
      log.info("Successfully loaded {} drivers into the database", drivers.size());
    } catch (IOException e) {
      log.error("Failed to load drivers from JSON: {}", e.getMessage(), e);
      throw new RuntimeException("Data initialization failed", e);
    }
  }

  private List<Driver> readDriversFromJson() throws IOException {
    Resource resource = new ClassPathResource(DRIVERS_JSON_PATH);
    try (InputStream inputStream = resource.getInputStream()) {
      return objectMapper.readValue(inputStream, new TypeReference<List<Driver>>() {});
    }
  }
}
