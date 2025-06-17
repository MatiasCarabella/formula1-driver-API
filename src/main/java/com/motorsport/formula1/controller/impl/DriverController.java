package com.motorsport.formula1.controller.impl;

import com.motorsport.formula1.entity.Driver;
import com.motorsport.formula1.usecase.ICreateDrivers;
import com.motorsport.formula1.usecase.IDeleteDriver;
import com.motorsport.formula1.usecase.IGetDriversWithFilters;
import com.motorsport.formula1.usecase.IInitializeDatabase;
import com.motorsport.formula1.usecase.IUpdateDriver;
import com.motorsport.formula1.util.DocumentationHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/drivers")
@Validated
@AllArgsConstructor
@Tag(name = "Driver Controller", description = "API for managing Formula 1 drivers")
public class DriverController {

  private final ICreateDrivers createDrivers;
  private final IGetDriversWithFilters getDriversWithFilters;
  private final IUpdateDriver updateDriver;
  private final IDeleteDriver deleteDriver;
  private final IInitializeDatabase initializeDatabase;

  @Operation(
      summary = "Get drivers with optional filters",
      description = "Fetch a list of drivers filtered by name, team, position, or year.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Drivers fetched successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Success",
                            value = DocumentationHelper.DRIVER_LIST_SUCCESS))),
        @ApiResponse(
            responseCode = "500",
            description = "Error fetching drivers",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Error",
                            value = DocumentationHelper.DRIVER_DELETE_ERROR)))
      })
  @GetMapping
  public ResponseEntity<Object> getDrivers(
      @Parameter(description = "Driver name") @RequestParam(required = false)
          Optional<String> driver,
      @Parameter(description = "Team name") @RequestParam(required = false) Optional<String> team,
      @Parameter(description = "Position") @RequestParam(required = false)
          Optional<Integer> position,
      @Parameter(description = "Year") @RequestParam(required = false) Optional<Integer> year) {
    return getDriversWithFilters.execute(driver, team, position, year);
  }

  @Operation(summary = "Add new drivers", description = "Add new drivers to the database.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Drivers added successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Success",
                            value = DocumentationHelper.DRIVER_ADD_SUCCESS))),
        @ApiResponse(
            responseCode = "400",
            description = "No drivers provided",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Error",
                            value = DocumentationHelper.DRIVER_ADD_ERROR)))
      })
  @PostMapping
  public ResponseEntity<Object> addDrivers(
      @Parameter(description = "List of drivers to add") @RequestBody List<Driver> drivers) {
    return createDrivers.execute(drivers);
  }

  @Operation(summary = "Update a driver", description = "Update an existing driver by ID.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Driver updated successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Success",
                            value = DocumentationHelper.DRIVER_UPDATE_SUCCESS))),
        @ApiResponse(
            responseCode = "404",
            description = "Driver not found",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Not Found",
                            value = DocumentationHelper.DRIVER_NOT_FOUND))),
        @ApiResponse(
            responseCode = "500",
            description = "Error updating driver",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Error",
                            value = DocumentationHelper.DRIVER_UPDATE_ERROR)))
      })
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateDriver(
      @Parameter(description = "Driver ID") @PathVariable Long id,
      @Parameter(description = "Driver data") @RequestBody Driver driver) {
    return updateDriver.execute(id, driver);
  }

  @Operation(
      summary = "Delete a driver",
      description = "Delete a driver by ID.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Driver deleted successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Success",
                            value = DocumentationHelper.DRIVER_DELETE_SUCCESS))),
        @ApiResponse(
            responseCode = "404",
            description = "Driver not found",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Not Found",
                            value = DocumentationHelper.DRIVER_NOT_FOUND))),
        @ApiResponse(
            responseCode = "500",
            description = "Error deleting driver",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Error",
                            value = DocumentationHelper.DRIVER_DELETE_ERROR)))
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteDriver(
      @Parameter(description = "Driver ID") @PathVariable Long id) {
    return deleteDriver.execute(id);
  }

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
                            value = DocumentationHelper.INIT_SUCCESS))),
        @ApiResponse(
            responseCode = "409",
            description = "Already initialized",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "Already initialized",
                            value = DocumentationHelper.INIT_ALREADY))),
        @ApiResponse(
            responseCode = "500",
            description = "Error",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(name = "Error", value = DocumentationHelper.INIT_ERROR)))
      })
  @PostMapping("/initialize")
  public ResponseEntity<Object> initializeDatabase() {
    return initializeDatabase.execute();
  }
}
