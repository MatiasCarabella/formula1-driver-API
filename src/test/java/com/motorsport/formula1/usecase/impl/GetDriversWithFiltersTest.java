package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GetDriversWithFiltersTest implements WithAssertions {
  @Mock DriverRepository driverRepository;
  @InjectMocks GetDriversWithFilters getDriversWithFilters;

  @SuppressWarnings("unchecked")
  @Test
  void shouldReturnNotFoundWhenNoResults() {
    when(driverRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());
    ResponseEntity<Object> response =
        getDriversWithFilters.execute(
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @SuppressWarnings("unchecked")
  @Test
  void shouldReturnOkWhenResultsFound() {
    when(driverRepository.findAll(any(Specification.class))).thenReturn(List.of(new Driver()));
    ResponseEntity<Object> response =
        getDriversWithFilters.execute(
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @SuppressWarnings("unchecked")
  @Test
  void shouldReturnInternalServerErrorOnException() {
    when(driverRepository.findAll(any(Specification.class))).thenThrow(new RuntimeException());
    ResponseEntity<Object> response =
        getDriversWithFilters.execute(
            Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
