package com.motorsport.formula1.usecase;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

/** IGetDriversWithFilters interface. */
@FunctionalInterface
public interface IGetDriversWithFilters {
  /**
   * Retrieve a filtered list of drivers based on search criteria.
   *
   * @param driver Optional driver name to search for
   * @param team Optional team name to search for
   * @param position Optional position to filter by
   * @param year Optional year to filter by
   * @return ResponseEntity containing the filtered list of drivers
   */
  ResponseEntity<Object> execute(
      Optional<String> driver,
      Optional<String> team,
      Optional<Integer> position,
      Optional<Integer> year);
}
