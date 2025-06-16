package com.motorsport.formula1.usecase;

/** IIsDatabasePopulated interface. */
@FunctionalInterface
public interface IIsDatabasePopulated {
  /**
   * Returns true if the database is populated with drivers, false otherwise.
   *
   * @return boolean
   * @throws Exception if an error occurs while checking the database state
   */
  boolean execute();
}
