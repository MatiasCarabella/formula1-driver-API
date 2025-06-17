package com.motorsport.formula1.usecase;

import com.motorsport.formula1.entity.Driver;
import java.util.List;

/** IGetAllDrivers interface. */
@FunctionalInterface
public interface IGetAllDrivers {
  /**
   * Retrieves all drivers from the repository.
   *
   * @return List<Driver> containing all drivers
   */
  List<Driver> execute();
}
