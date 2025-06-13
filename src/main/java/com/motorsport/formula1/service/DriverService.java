package com.motorsport.formula1.service;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DriverService {
    
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
    private final DriverRepository driverRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DriverService(DriverRepository driverRepository, ObjectMapper objectMapper) {
        this.driverRepository = driverRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Get all drivers from the database without any filtering.
     * Used primarily for initialization checks.
     *
     * @return List of all drivers in the database
     */
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll(Sort.by("id"));
    }

    /**
     * Retrieve a filtered list of drivers based on search criteria.
     *
     * @param driver   Optional driver name to search for
     * @param team     Optional team name to search for
     * @param position Optional position to filter by
     * @param year     Optional year to filter by
     * @return ResponseEntity containing the filtered list of drivers
     */
    public ResponseEntity<Object> get(Optional<String> driver,
            Optional<String> team,
            Optional<Integer> position,
            Optional<Integer> year) {
        try {
            Specification<Driver> spec = buildSpecification(driver, team, position, year);
            List<Driver> drivers = driverRepository.findAll(spec, Sort.by("id"));

            return CollectionUtils.isEmpty(drivers)
                    ? ResponseHandler.generateResponse("No results found", HttpStatus.NOT_FOUND, drivers)
                    : ResponseHandler.generateResponse("Success", HttpStatus.OK, drivers);
        } catch (Exception e) {
            logger.error("Error retrieving drivers: ", e);
            return ResponseHandler.generateResponse("Error retrieving drivers", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * Add a list of new drivers to the database.
     *
     * @param drivers List of drivers to add
     * @return ResponseEntity indicating success or failure
     */
    @Transactional
    public ResponseEntity<Object> add(List<Driver> drivers) {
        try {
            if (CollectionUtils.isEmpty(drivers)) {
                return ResponseHandler.generateResponse("No drivers provided", HttpStatus.BAD_REQUEST, null);
            }

            List<Driver> existingDrivers = findExistingDrivers(drivers);

            if (!existingDrivers.isEmpty()) {
                return ResponseHandler.generateResponse("Existing drivers detected", HttpStatus.CONFLICT,
                        existingDrivers);
            }

            List<Driver> savedDrivers = drivers.stream()
                    .map(driverRepository::save)
                    .collect(Collectors.toList());

            return ResponseHandler.generateResponse("Drivers created successfully", HttpStatus.CREATED, savedDrivers);
        } catch (Exception e) {
            logger.error("Error adding drivers: ", e);
            return ResponseHandler.generateResponse("Error adding drivers", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * Update an existing driver's information.
     *
     * @param id        ID of the driver to update
     * @param newDriver Updated driver information
     * @return ResponseEntity indicating success or failure
     */
    @Transactional
    public ResponseEntity<Object> update(Long id, Driver newDriver) {
        try {
            return driverRepository.findById(id)
                    .map(existingDriver -> updateExistingDriver(existingDriver, newDriver))
                    .orElse(ResponseHandler.generateResponse(
                            "Driver with ID " + id + " does not exist",
                            HttpStatus.NOT_FOUND,
                            null));
        } catch (Exception e) {
            logger.error("Error updating driver: ", e);
            return ResponseHandler.generateResponse("Error updating driver", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * Delete a driver by ID.
     *
     * @param id ID of the driver to delete
     * @return ResponseEntity indicating success or failure
     */
    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        try {
            Optional<Driver> driver = driverRepository.findById(id);
            if (driver.isEmpty()) {
                return ResponseHandler.generateResponse(
                        "Driver with ID " + id + " does not exist",
                        HttpStatus.NOT_FOUND,
                        null);
            }

            driverRepository.deleteById(id);
            return ResponseHandler.generateResponse("Driver deleted successfully", HttpStatus.OK, driver.get());
        } catch (Exception e) {
            logger.error("Error deleting driver: ", e);
            return ResponseHandler.generateResponse("Error deleting driver", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * Initialize the database with sample driver data.
     *
     * @return ResponseEntity indicating success or failure
     */
    @Transactional
    public ResponseEntity<Object> initializeData() {
        try {
            if (isDatabasePopulated()) {
                return ResponseHandler.generateResponse("Drivers already exist in the database. Skipping initialization.", HttpStatus.OK, null);
            }

            loadDriversFromJson();
            return ResponseHandler.generateResponse("Successfully initialized driver data.", HttpStatus.OK, null);
        } catch (IOException e) {
            logger.error("Failed to initialize data: ", e);
            return ResponseHandler.generateResponse("Failed to initialize data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Private helper methods

    private Specification<Driver> buildSpecification(Optional<String> driver,
            Optional<String> team,
            Optional<Integer> position,
            Optional<Integer> year) {
        return (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            driver.ifPresent(d -> {
                String pattern = "%" + d.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("name")), pattern));
            });

            team.ifPresent(t -> {
                String pattern = "%" + t.toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get("team")), pattern));
            });

            position.ifPresent(p -> predicates.add(cb.equal(root.get("position"), p)));

            year.ifPresent(y -> predicates.add(cb.equal(root.get("year"), y)));

            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }


    private List<Driver> findExistingDrivers(List<Driver> drivers) {
        return drivers.stream()
                .filter(driver -> driverRepository.existsByNameAndYearAndTeam(
                        driver.getName(),
                        driver.getYear(),
                        driver.getTeam()))
                .collect(Collectors.toList());
    }

    private ResponseEntity<Object> updateExistingDriver(Driver existingDriver, Driver newDriver) {
        if (driverRepository.existsByNameAndYearAndTeam(
                newDriver.getName(),
                newDriver.getYear(),
                newDriver.getTeam())) {
            return ResponseHandler.generateResponse("Driver already exists", HttpStatus.CONFLICT, null);
        }

        existingDriver.setYear(newDriver.getYear());
        existingDriver.setName(newDriver.getName());
        existingDriver.setTeam(newDriver.getTeam());
        Driver updatedDriver = driverRepository.save(existingDriver);

        return ResponseHandler.generateResponse("Driver updated successfully", HttpStatus.OK, updatedDriver);
    }

    private boolean isDatabasePopulated() {
        List<Driver> existingDrivers = driverRepository.findAll();
        return !CollectionUtils.isEmpty(existingDrivers);
    }

    private void loadDriversFromJson() throws IOException {
        List<Driver> drivers = readDriversFromJson();
        driverRepository.saveAll(drivers);
    }

    private List<Driver> readDriversFromJson() throws IOException {
        Resource resource = new ClassPathResource("/data/drivers.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Driver>>() {});
        }
    }

    
}