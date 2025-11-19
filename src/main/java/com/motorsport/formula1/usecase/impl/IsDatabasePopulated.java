package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.usecase.IGetAllDrivers;
import com.motorsport.formula1.usecase.IIsDatabasePopulated;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
@Slf4j
public class IsDatabasePopulated implements IIsDatabasePopulated {
  private final IGetAllDrivers getAllDrivers;

  @Override
  public boolean execute() {
    try {
      final List<Driver> existingDrivers = getAllDrivers.execute();
      return !CollectionUtils.isEmpty(existingDrivers);
    } catch (Exception e) {
      log.error("Error checking database state: {}", e.getMessage(), e);
      return false;
    }
  }
}
