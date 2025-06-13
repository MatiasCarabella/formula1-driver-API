package com.motorsport.formula1.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private static final String DRIVERS_JSON_PATH = "/data/drivers.json";

    private final ObjectMapper objectMapper;
    private final DriverService driverService;

    public DataInitializer(ObjectMapper objectMapper, DriverService driverService) {
        this.objectMapper = objectMapper;
        this.driverService = driverService;
    }

    // Remove @Bean annotation to prevent automatic execution on startup
    public void loadDrivers() {
        if (isDatabasePopulated()) {
            logger.info("Drivers already exist in the database. Skipping initialization.");
            return;
        }
        loadDriversFromJson();
    }

    private boolean isDatabasePopulated() {
        try {
            List<Driver> existingDrivers = driverService.getAllDrivers();
            return !CollectionUtils.isEmpty(existingDrivers);
        } catch (Exception e) {
            logger.error("Error checking database state: {}", e.getMessage(), e);
            return false;
        }
    }

    private void loadDriversFromJson() {
        try {
            List<Driver> drivers = readDriversFromJson();
            driverService.add(drivers);
            logger.info("Successfully loaded {} drivers into the database", drivers.size());
        } catch (IOException e) {
            logger.error("Failed to load drivers from JSON: {}", e.getMessage(), e);
            throw new RuntimeException("Data initialization failed", e);
        }
    }

    private List<Driver> readDriversFromJson() throws IOException {
        Resource resource = new ClassPathResource(DRIVERS_JSON_PATH);
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Driver>>() {
            });
        }
    }
}