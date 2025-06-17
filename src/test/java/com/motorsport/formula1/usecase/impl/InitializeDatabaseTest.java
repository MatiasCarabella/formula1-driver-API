package com.motorsport.formula1.usecase.impl;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

import com.motorsport.formula1.usecase.ICreateDrivers;
import com.motorsport.formula1.usecase.IGetDriversFromJson;
import com.motorsport.formula1.usecase.IIsDatabasePopulated;
import java.io.IOException;
import java.util.Collections;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class InitializeDatabaseTest implements WithAssertions {
  @Mock IIsDatabasePopulated isDatabasePopulated;
  @Mock IGetDriversFromJson getDriversFromJson;
  @Mock ICreateDrivers createDrivers;
  @InjectMocks InitializeDatabase initializeDatabase;

  @Test
  void shouldReturnConflictWhenAlreadyInitialized() {
    when(isDatabasePopulated.execute()).thenReturn(true);
    ResponseEntity<Object> response = initializeDatabase.execute();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  void shouldReturnOkWhenInitializedSuccessfully() throws Exception {
    when(isDatabasePopulated.execute()).thenReturn(false);
    when(getDriversFromJson.execute()).thenReturn(Collections.emptyList());
    when(createDrivers.execute(anyList())).thenReturn(ResponseEntity.ok().build());
    ResponseEntity<Object> response = initializeDatabase.execute();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void shouldReturnInternalServerErrorOnIOException() throws Exception {
    when(isDatabasePopulated.execute()).thenReturn(false);
    when(getDriversFromJson.execute()).thenThrow(new IOException("fail"));
    ResponseEntity<Object> response = initializeDatabase.execute();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
