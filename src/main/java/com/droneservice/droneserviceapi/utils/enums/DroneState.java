package com.droneservice.droneserviceapi.utils.enums;

public enum DroneState {

    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private String state;

    DroneState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static DroneState getEnumFromString(String state) {
        if( state != null ) {
            try {
                return DroneState.valueOf(state.trim());
            }
            catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
