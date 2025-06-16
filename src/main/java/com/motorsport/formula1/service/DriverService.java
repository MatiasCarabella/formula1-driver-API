package com.motorsport.formula1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class DriverService {
  private final DriverRepository driverRepository;
  private final ObjectMapper objectMapper;

  /**
   * Get all drivers from the database without any filtering. Used primarily for initialization
   * checks.
   *
   * @return List of all drivers in the database
   */
  public List<Driver> getAllDrivers() {
    return driverRepository.findAll();
  }

  /**
   * Retrieve a filtered list of drivers based on search criteria.
   *
   * @param driver Optional driver name to search for
   * @param team Optional team name to search for
   * @param position Optional position to filter by
   * @param year Optional year to filter by
   * @return ResponseEntity containing the filtered list of drivers
   */
  public ResponseEntity<Object> get(
      Optional<String> driver,
      Optional<String> team,
      Optional<Integer> position,
      Optional<Integer> year) {
    try {
      Specification<Driver> spec = buildSpecification(driver, team, position, year);
      List<Driver> drivers = driverRepository.findAll(spec);

      return CollectionUtils.isEmpty(drivers)
          ? Response.generate("No results found", HttpStatus.NOT_FOUND, drivers)
          : Response.generate("Success", HttpStatus.OK, drivers);
    } catch (Exception e) {
      log.error("Error retrieving drivers: ", e);
      return Response.generate("Error retrieving drivers", HttpStatus.INTERNAL_SERVER_ERROR);
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
        return Response.generate("No drivers provided", HttpStatus.BAD_REQUEST);
      }

      List<Driver> existingDrivers = findExistingDrivers(drivers);

      if (!existingDrivers.isEmpty()) {
        return Response.generate("Existing drivers detected", HttpStatus.CONFLICT, existingDrivers);
      }

      List<Driver> savedDrivers =
          drivers.stream().map(driverRepository::save).collect(Collectors.toList());

      return Response.generate("Drivers created successfully", HttpStatus.CREATED, savedDrivers);
    } catch (Exception e) {
      log.error("Error adding drivers: ", e);
      return Response.generate("Error adding drivers", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Update an existing driver's information.
   *
   * @param id ID of the driver to update
   * @param newDriver Updated driver information
   * @return ResponseEntity indicating success or failure
   */
  @Transactional
  public ResponseEntity<Object> update(Long id, Driver newDriver) {
    try {
      return driverRepository
          .findById(id)
          .map(existingDriver -> updateExistingDriver(existingDriver, newDriver))
          .orElse(
              Response.generate("Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      log.error("Error updating driver: ", e);
      return Response.generate("Error updating driver", HttpStatus.INTERNAL_SERVER_ERROR);
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
        return Response.generate("Driver with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
      }

      driverRepository.deleteById(id);
      return Response.generate("Driver deleted successfully", HttpStatus.OK, driver.get());
    } catch (Exception e) {
      log.error("Error deleting driver: ", e);
      return Response.generate("Error deleting driver", HttpStatus.INTERNAL_SERVER_ERROR);
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
        return Response.generate(
            "Drivers already exist in the database. Skipping initialization.", HttpStatus.OK);
      }

      loadDriversFromJson();
      return Response.generate("Successfully initialized driver data.", HttpStatus.OK);
    } catch (IOException e) {
      log.error("Failed to initialize data: ", e);
      return Response.generate(
          "Failed to initialize data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Private helper methods

  private Specification<Driver> buildSpecification(
      Optional<String> driver,
      Optional<String> team,
      Optional<Integer> position,
      Optional<Integer> year) {
    return (root, query, cb) -> {
      List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

      driver.ifPresent(
          d -> {
            String pattern = "%" + d.toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get("name")), pattern));
          });

      team.ifPresent(
          t -> {
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
        .filter(
            driver ->
                driverRepository.existsByNameAndYearAndTeam(
                    driver.getName(), driver.getYear(), driver.getTeam()))
        .collect(Collectors.toList());
  }

  private ResponseEntity<Object> updateExistingDriver(Driver existingDriver, Driver newDriver) {
    if (driverRepository.existsByNameAndYearAndTeam(
        newDriver.getName(), newDriver.getYear(), newDriver.getTeam())) {
      return Response.generate("Driver already exists", HttpStatus.CONFLICT);
    }

    existingDriver.setYear(newDriver.getYear());
    existingDriver.setName(newDriver.getName());
    existingDriver.setTeam(newDriver.getTeam());
    Driver updatedDriver = driverRepository.save(existingDriver);

    return Response.generate("Driver updated successfully", HttpStatus.OK, updatedDriver);
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
