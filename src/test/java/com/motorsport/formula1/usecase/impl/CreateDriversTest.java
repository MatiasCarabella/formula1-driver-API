package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import com.motorsport.formula1.usecase.IGetDuplicateDrivers;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CreateDriversTest implements WithAssertions {

  @Mock DriverRepository driverRepository;
  @Mock IGetDuplicateDrivers getDuplicateDrivers;
  @InjectMocks CreateDrivers createDrivers;

  @Test
  void shouldReturnBadRequestWhenNoDriversProvided() {
    ResponseEntity<Object> response = createDrivers.execute(Collections.emptyList());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void shouldReturnConflictWhenDuplicatesExist() {
    List<Driver> drivers = List.of(new Driver());
    when(getDuplicateDrivers.execute(drivers)).thenReturn(drivers);
    ResponseEntity<Object> response = createDrivers.execute(drivers);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  void shouldReturnCreatedWhenDriversAreCreated() {
    List<Driver> drivers = List.of(new Driver());
    when(getDuplicateDrivers.execute(drivers)).thenReturn(Collections.emptyList());
    when(driverRepository.save(any(Driver.class))).thenReturn(new Driver());
    ResponseEntity<Object> response = createDrivers.execute(drivers);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  void shouldReturnInternalServerErrorOnException() {
    List<Driver> drivers = List.of(new Driver());
    when(getDuplicateDrivers.execute(drivers)).thenThrow(new RuntimeException());
    ResponseEntity<Object> response = createDrivers.execute(drivers);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
