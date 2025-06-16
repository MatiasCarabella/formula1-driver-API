package com.motorsport.formula1.usecase;

import com.motorsport.formula1.domain.Driver;
import java.util.List;

/** IGetDuplicateDrivers interface. */
@FunctionalInterface
public interface IGetDuplicateDrivers {
  /**
   * Finds and returns a list of duplicate drivers from the provided list.
   *
   * @param drivers List of Driver objects to check for duplicates
   * @return List<Driver> containing duplicate drivers
   */
  List<Driver> execute(List<Driver> drivers);
}
