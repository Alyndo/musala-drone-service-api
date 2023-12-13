package com.droneservice.droneserviceapi.utils.enums;

public enum DroneStateAction {

    REGISTER("REGISTER"),
    LOAD("LOAD"),
    INSPECT_LOAD("INSPECT_LOAD"),
    DEPART("DEPART"),
    ARRIVE("ARRIVE"),
    RETURN("RETURN");

    private String action;

    DroneStateAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public static DroneStateAction getEnumFromString(String action) {
        if( action != null ) {
            try {
                return DroneStateAction.valueOf(action.trim());
            }
            catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
