package com.droneservice.droneserviceapi.controller;

import com.droneservice.droneserviceapi.service.DroneService;
import com.droneservice.droneserviceapi.utils.request.DroneRegistrationRequest;
import com.droneservice.droneserviceapi.utils.request.DroneStateChangeRequest;
import com.droneservice.droneserviceapi.utils.request.LoadMedicationRequest;
import com.droneservice.droneserviceapi.utils.response.BatteryLevelResponse;
import com.droneservice.droneserviceapi.utils.response.CommonResponse;
import com.droneservice.droneserviceapi.utils.response.DroneResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static com.droneservice.droneserviceapi.utils.dto.Constants.DEFAULT_LOCALE_LANGUAGE;
import static com.droneservice.droneserviceapi.utils.dto.Constants.LOCALE_LANGUAGE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drones")
public class DroneController {

    private final DroneService droneService;

    @Operation(summary = "Register a Drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drone registered successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))})})
    @PostMapping
    public ResponseEntity<DroneResponse> registerDrone(@RequestBody @Valid DroneRegistrationRequest droneRegistrationRequest,
                                                       @RequestHeader(name = LOCALE_LANGUAGE,
                                                               defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        DroneResponse savedDrone = droneService.registerDrone(droneRegistrationRequest, Locale.forLanguageTag(language));
        return new ResponseEntity<>(savedDrone, HttpStatus.CREATED);
    }

    @Operation(summary = "Force Change Drone State : Only For Testing Purposes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone State updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Drone Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))}),
    })
    @PutMapping
    public ResponseEntity<DroneResponse> ChangeDroneState(@RequestBody @Valid DroneStateChangeRequest request,
                                                          @RequestHeader(name = LOCALE_LANGUAGE,
                                                                  defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        DroneResponse savedDrone = droneService.changeDroneState(request, Locale.forLanguageTag(language));
        return new ResponseEntity<>(savedDrone, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All available drones for loading")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Drones Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))})})
    @GetMapping("/available")
    public ResponseEntity<DroneResponse> getAvailableDronesForLoading(@RequestHeader(name = LOCALE_LANGUAGE,
            defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        DroneResponse availableDrones = droneService.getAvailableDronesForLoading(Locale.forLanguageTag(language));
        return new ResponseEntity<>(availableDrones, HttpStatus.OK);
    }

    @Operation(summary = "Get Loaded Medications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medication retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Drone Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))})})
    @GetMapping("/{serialNumber}/medications")
    public ResponseEntity<DroneResponse> getLoadedMedication(
            @PathVariable String serialNumber, @RequestHeader(name = LOCALE_LANGUAGE,
            defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        DroneResponse availableDrones = droneService.getLoadedMedication(serialNumber, Locale.forLanguageTag(language));
        return new ResponseEntity<>(availableDrones, HttpStatus.OK);
    }

    @Operation(summary = "Check Battery Level")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Battery checked successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BatteryLevelResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Drone Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))})})
    @GetMapping("/{serialNumber}/battery")
    public ResponseEntity<BatteryLevelResponse> getDroneBatteryLevel(
            @PathVariable String serialNumber, @RequestHeader(name = LOCALE_LANGUAGE,
            defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        BatteryLevelResponse response = droneService.getDroneBatteryLevel(serialNumber, Locale.forLanguageTag(language));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Load Medications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medications successfully loaded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Drone Not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))}),
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))})
    })
    @PutMapping("/{serialNumber}/medications")
    public ResponseEntity<DroneResponse> loadMedications(
            @PathVariable String serialNumber,
            @RequestBody @Valid LoadMedicationRequest medicationRequest,
            @RequestHeader(name = LOCALE_LANGUAGE,
                    defaultValue = DEFAULT_LOCALE_LANGUAGE) final String language) {
        DroneResponse response = droneService.loadMedications(serialNumber, medicationRequest, Locale.forLanguageTag(language));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

