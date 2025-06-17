package com.motorsport.formula1.usecase.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.repository.DriverRepository;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDuplicateDriversTest implements WithAssertions {
  @Mock DriverRepository driverRepository;
  @InjectMocks GetDuplicateDrivers getDuplicateDrivers;

  @Test
  void shouldReturnEmptyListWhenNoDuplicates() {
    List<Driver> drivers =
        List.of(
            Driver.builder().name("A").year(2024).team("T1").build(),
            Driver.builder().name("B").year(2024).team("T2").build());
    Mockito.when(driverRepository.existsByNameAndYearAndTeam("A", 2024, "T1")).thenReturn(false);
    Mockito.when(driverRepository.existsByNameAndYearAndTeam("B", 2024, "T2")).thenReturn(false);
    List<Driver> result = getDuplicateDrivers.execute(drivers);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnDuplicates() {
    Driver d1 = Driver.builder().name("A").year(2024).team("T1").build();
    Driver d2 = Driver.builder().name("A").year(2024).team("T1").build();
    List<Driver> drivers = List.of(d1, d2);
    Mockito.when(driverRepository.existsByNameAndYearAndTeam("A", 2024, "T1")).thenReturn(true);
    List<Driver> result = getDuplicateDrivers.execute(drivers);
    assertThat(result).containsExactly(d1, d2);
  }
}
