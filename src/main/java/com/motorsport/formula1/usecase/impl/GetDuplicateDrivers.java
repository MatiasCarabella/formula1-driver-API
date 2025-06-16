package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.usecase.IGetDuplicateDrivers;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetDuplicateDrivers implements IGetDuplicateDrivers {
  private final DriverRepository driverRepository;

  @Override
  public List<Driver> execute(List<Driver> drivers) {
    return drivers.stream()
        .filter(
            driver ->
                driverRepository.existsByNameAndYearAndTeam(
                    driver.getName(), driver.getYear(), driver.getTeam()))
        .collect(Collectors.toList());
  }
}
