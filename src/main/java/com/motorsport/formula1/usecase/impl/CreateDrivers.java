package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.domain.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.response.Response;
import com.motorsport.formula1.usecase.ICreateDrivers;
import com.motorsport.formula1.usecase.IGetDuplicateDrivers;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
@Slf4j
public class CreateDrivers implements ICreateDrivers {
  private final DriverRepository driverRepository;
  private final IGetDuplicateDrivers getDuplicateDrivers;

  @Override
  public ResponseEntity<Object> execute(List<Driver> drivers) {
    try {
      if (CollectionUtils.isEmpty(drivers)) {
        return Response.generate("No drivers provided", HttpStatus.BAD_REQUEST);
      }

      List<Driver> duplicateDrivers = getDuplicateDrivers.execute(drivers);

      if (!duplicateDrivers.isEmpty()) {
        return Response.generate(
            "Existing drivers detected", HttpStatus.CONFLICT, duplicateDrivers);
      }

      List<Driver> createdDrivers = drivers.stream().map(driverRepository::save).toList();

      return Response.generate("Drivers created successfully", HttpStatus.CREATED, createdDrivers);
    } catch (Exception e) {
      log.error("Error creating drivers: ", e);
      return Response.generate("Error creating drivers", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
