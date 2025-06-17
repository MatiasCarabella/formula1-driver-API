package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UpdateDriverTest implements WithAssertions {
  @Mock DriverRepository driverRepository;
  @InjectMocks UpdateDriver updateDriver;

  @Test
  void shouldReturnNotFoundWhenDriverDoesNotExist() {
    when(driverRepository.findById(1L)).thenReturn(Optional.empty());
    ResponseEntity<Object> response = updateDriver.execute(1L, new Driver());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void shouldReturnConflictWhenDriverAlreadyExists() {
    Driver existing = Driver.builder().build();
    Driver input = Driver.builder().name("Lewis Hamilton").year(2024).team("Mercedes").build();
    when(driverRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(driverRepository.existsByNameAndYearAndTeam(
            eq("Lewis Hamilton"), eq(2024), eq("Mercedes")))
        .thenReturn(true);
    ResponseEntity<Object> response = updateDriver.execute(1L, input);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  void shouldReturnOkWhenDriverIsUpdated() {
    Driver existing = Driver.builder().build();
    Driver input = Driver.builder().name("Lewis Hamilton").year(2024).team("Mercedes").build();
    when(driverRepository.findById(1L)).thenReturn(Optional.of(existing));
    when(driverRepository.existsByNameAndYearAndTeam(
            eq("Lewis Hamilton"), eq(2024), eq("Mercedes")))
        .thenReturn(false);
    when(driverRepository.save(any(Driver.class))).thenReturn(existing);
    ResponseEntity<Object> response = updateDriver.execute(1L, input);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void shouldReturnInternalServerErrorOnException() {
    when(driverRepository.findById(anyLong())).thenThrow(new RuntimeException());
    ResponseEntity<Object> response = updateDriver.execute(1L, new Driver());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
