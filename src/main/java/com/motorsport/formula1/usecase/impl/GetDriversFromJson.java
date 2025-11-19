package com.motorsport.formula1.usecase.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.usecase.IGetDriversFromJson;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetDriversFromJson implements IGetDriversFromJson {
  private static final String DRIVERS_JSON_PATH = "/data/drivers.json";
  private final ObjectMapper objectMapper;

  public List<Driver> execute() throws IOException {
    final Resource resource = new ClassPathResource(DRIVERS_JSON_PATH);
    try (InputStream inputStream = resource.getInputStream()) {
      return objectMapper.readValue(inputStream, new TypeReference<List<Driver>>() {});
    }
  }
}
