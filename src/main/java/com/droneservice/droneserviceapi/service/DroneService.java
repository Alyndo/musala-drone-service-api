package com.droneservice.droneserviceapi.service;

import com.droneservice.droneserviceapi.utils.request.DroneRegistrationRequest;
import com.droneservice.droneserviceapi.utils.request.DroneStateChangeRequest;
import com.droneservice.droneserviceapi.utils.request.LoadMedicationRequest;
import com.droneservice.droneserviceapi.utils.response.BatteryLevelResponse;
import com.droneservice.droneserviceapi.utils.response.DroneResponse;

import java.util.Locale;

public interface DroneService {
    DroneResponse registerDrone(DroneRegistrationRequest droneRegistrationRequest, Locale locale);

    DroneResponse changeDroneState(DroneStateChangeRequest request, Locale locale);

    DroneResponse getAvailableDronesForLoading(Locale locale);

    DroneResponse getLoadedMedication(String serialNumber, Locale locale);

    BatteryLevelResponse getDroneBatteryLevel(String serialNumber, Locale locale);

    DroneResponse loadMedications(String serialNumber, LoadMedicationRequest medicationRequest, Locale locale);
}
