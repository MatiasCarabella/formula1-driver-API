package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.usecase.IGetAllDrivers;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetAllDrivers implements IGetAllDrivers {
  private final DriverRepository driverRepository;

  @Override
  public List<Driver> execute() {
    return driverRepository.findAll();
  }
}
