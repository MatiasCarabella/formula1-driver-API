package com.motorsport.formula1.controller;

import com.motorsport.formula1.domain.Driver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Driver Controller", description = "API for managing Formula 1 drivers")
public interface IDriverController {

  @Operation(
      summary = "Get drivers with optional filters",
      description = "Fetch a list of drivers filtered by name, team, position, or year.")
  ResponseEntity<Object> getDrivers(
      @Parameter(description = "Driver name") @RequestParam(required = false)
          Optional<String> driver,
      @Parameter(description = "Team name") @RequestParam(required = false) Optional<String> team,
      @Parameter(description = "Position") @RequestParam(required = false)
          Optional<Integer> position,
      @Parameter(description = "Year") @RequestParam(required = false) Optional<Integer> year);

  @Operation(summary = "Add new drivers", description = "Add new drivers to the database.")
  ResponseEntity<Object> addDrivers(
      @Parameter(description = "List of drivers to add") @RequestBody List<Driver> drivers);

  @Operation(summary = "Update a driver", description = "Update an existing driver by ID.")
  ResponseEntity<Object> updateDriver(
      @Parameter(description = "Driver ID") @PathVariable Long id,
      @Parameter(description = "Driver data") @RequestBody Driver driver);

  @Operation(summary = "Delete a driver", description = "Delete a driver by ID.")
  ResponseEntity<Object> deleteDriver(@Parameter(description = "Driver ID") @PathVariable Long id);

  @Operation(
      summary = "Initialize driver data",
      description = "Initialize the database with pre-defined driver data.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Initialization successful",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Success",
                            value =
                                "{\n  \"message\": \"Successfully initialized driver data.\",\n  \"status\": 200\n}"))),
        @ApiResponse(
            responseCode = "409",
            description = "Drivers already exist in the database. Skipping initialization.",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Already Exists",
                            value =
                                "{\n  \"message\": \"Drivers already exist in the database. Skipping initialization.\",\n  \"status\": 409\n}"))),
        @ApiResponse(
            responseCode = "500",
            description = "Initialization failed",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Error",
                            value =
                                "{\n  \"message\": \"Failed to initialize data: <error message>\",\n  \"status\": 500\n}")))
      })
  ResponseEntity<Object> initializeData();
}
