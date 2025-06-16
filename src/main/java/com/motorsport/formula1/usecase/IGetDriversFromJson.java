package com.motorsport.formula1.usecase;

import com.motorsport.formula1.domain.Driver;
import java.io.IOException;
import java.util.List;

/** IGetDriversFromJson interface. */
@FunctionalInterface
public interface IGetDriversFromJson {
  /**
   * Gets drivers from a JSON file and returns a list of Driver objects.
   *
   * @return List<Driver>
   * @throws IOException
   */
  List<Driver> execute() throws IOException;
}
