package com.motorsport.formula1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IStatusController {
  @Operation(
      summary = "Check the service status",
      description = "Returns a simple status message indicating the service is running.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Service is up and running",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            value =
                                "{\n    \"message\": \"Ready to go! 🚦🏁\",\n    \"status\": 200\n}")))
      })
  ResponseEntity<Object> checkStatus();
}
