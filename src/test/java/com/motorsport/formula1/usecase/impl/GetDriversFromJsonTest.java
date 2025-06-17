package com.motorsport.formula1.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorsport.formula1.entity.Driver;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class GetDriversFromJsonTest implements WithAssertions {
  @Test
  void shouldReturnDriversFromJson() throws Exception {
    GetDriversFromJson getDriversFromJson = new GetDriversFromJson(new ObjectMapper());
    List<Driver> result = getDriversFromJson.execute();
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result.get(0).getName()).isNotBlank();
    assertThat(result.get(0).getTeam()).isNotBlank();
    assertThat(result.get(0).getYear()).isGreaterThan(1900);
  }
}
