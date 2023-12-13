package com.droneservice.droneserviceapi.utils.exceptions;

import com.droneservice.droneserviceapi.utils.enums.DroneState;
import com.droneservice.droneserviceapi.utils.enums.DroneStateAction;

public class DroneStateException extends RuntimeException {

    private final DroneState droneState;
    private final DroneStateAction action;

    public DroneStateException(DroneState droneState, DroneStateAction action) {
        this.droneState = droneState;
        this.action = action;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public DroneStateAction getAction() {
        return action;
    }

    @Override
    public String getMessage() {
        return "invalid action " + action + " on state " + droneState;
    }
}
