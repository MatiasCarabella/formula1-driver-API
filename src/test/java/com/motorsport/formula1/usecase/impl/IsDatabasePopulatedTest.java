package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.when;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.usecase.IGetAllDrivers;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsDatabasePopulatedTest implements WithAssertions {

  @InjectMocks private IsDatabasePopulated isDatabasePopulated;

  @Mock private IGetAllDrivers getAllDrivers;

  @Test
  void shouldReturnTrueWhenDriversExist() {
    when(getAllDrivers.execute()).thenReturn(List.of(new Driver()));
    assertThat(isDatabasePopulated.execute()).isTrue();
  }

  @Test
  void shouldReturnFalseWhenNoDriversExist() {
    when(getAllDrivers.execute()).thenReturn(Collections.emptyList());
    assertThat(isDatabasePopulated.execute()).isFalse();
  }

  @Test
  void shouldReturnFalseWhenExceptionIsThrown() {
    when(getAllDrivers.execute()).thenThrow(new RuntimeException("DB error"));
    assertThat(isDatabasePopulated.execute()).isFalse();
  }
}
