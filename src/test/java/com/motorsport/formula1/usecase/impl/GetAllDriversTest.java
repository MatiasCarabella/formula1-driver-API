package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.*;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllDriversTest implements WithAssertions {
  @Mock DriverRepository driverRepository;
  @InjectMocks GetAllDrivers getAllDrivers;

  @Test
  void shouldReturnAllDrivers() {
    List<Driver> drivers = List.of(Driver.builder().name("A").year(2024).team("T1").build());
    when(driverRepository.findAll()).thenReturn(drivers);
    List<Driver> result = getAllDrivers.execute();
    assertThat(result).isEqualTo(drivers);
  }

  @Test
  void shouldReturnEmptyListWhenNoDrivers() {
    when(driverRepository.findAll()).thenReturn(Collections.emptyList());
    List<Driver> result = getAllDrivers.execute();
    assertThat(result).isEmpty();
  }
}
