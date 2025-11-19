package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.ResponseHandler;
import com.motorsport.formula1.usecase.IGetDriversWithFilters;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
@Slf4j
public class GetDriversWithFilters implements IGetDriversWithFilters {
  private final DriverRepository driverRepository;

  @Override
  public ResponseEntity<Object> execute(
      Optional<String> driver,
      Optional<String> team,
      Optional<Integer> position,
      Optional<Integer> year) {
    try {
      final Specification<Driver> spec = buildSpecification(driver, team, position, year);
      final List<Driver> drivers = driverRepository.findAll(spec);

      return CollectionUtils.isEmpty(drivers)
          ? ResponseHandler.generate("No results found", HttpStatus.NOT_FOUND, drivers)
          : ResponseHandler.generate("Success", HttpStatus.OK, drivers);
    } catch (Exception e) {
      log.error("Error retrieving drivers: ", e);
      return ResponseHandler.generate("Error retrieving drivers", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Specification<Driver> buildSpecification(
      Optional<String> driver,
      Optional<String> team,
      Optional<Integer> position,
      Optional<Integer> year) {
    return (root, query, cb) -> {
      final List<Predicate> predicates = new ArrayList<>();

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

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
