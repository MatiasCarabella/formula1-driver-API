package com.springBootProject.formula1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.springBootProject.formula1.repository.DriverRepository;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DriverControllerIntegrationTests {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    MockMvc mockMvc;

    /**
     * TODO: ADD TESTS
    @Container
    private static MySQLContainer<?> container = new MySQLContainer<>("mysql:latest").withReuse(true);

    @DynamicPropertySource
    private static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
    */

    @BeforeEach
    void setup() {
        driverRepository.deleteAll();
    }

    @Test
    void testGetDrivers() {
        
    }

    @Test
    void testAddDriver() {

    }

    @Test
    void testDeleteDriver() {

    }

    @Test
    void testUpdateDriver() {

    }
}
