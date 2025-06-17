package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.anyLong;
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
class DeleteDriverTest implements WithAssertions {
  @Mock DriverRepository driverRepository;
  @InjectMocks DeleteDriver deleteDriver;

  @Test
  void shouldReturnNotFoundWhenDriverDoesNotExist() {
    when(driverRepository.findById(1L)).thenReturn(Optional.empty());
    ResponseEntity<Object> response = deleteDriver.execute(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void shouldReturnOkWhenDriverIsDeleted() {
    Driver driver = new Driver();
    when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
    ResponseEntity<Object> response = deleteDriver.execute(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void shouldReturnInternalServerErrorOnException() {
    when(driverRepository.findById(anyLong())).thenThrow(new RuntimeException());
    ResponseEntity<Object> response = deleteDriver.execute(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
