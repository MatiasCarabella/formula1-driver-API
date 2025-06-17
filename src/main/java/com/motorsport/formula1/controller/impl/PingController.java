package com.motorsport.formula1.controller.impl;

import com.motorsport.formula1.controller.IPingController;
import com.motorsport.formula1.response.ResponseHandler;
import com.motorsport.formula1.util.DocumentationHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class PingController implements IPingController {

  /** Ping endpoint to check service status */
  @Operation(
      summary = "Ping F1 Driver API",
      description = "Status check endpoint to verify if the API is running.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "API is up and running",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(name = "Ping Success", value = DocumentationHelper.PING)))
      })
  @GetMapping
  public ResponseEntity<Object> ping() {
    return ResponseHandler.generate("Ready to go! 🚦🏁", HttpStatus.OK);
  }
}
