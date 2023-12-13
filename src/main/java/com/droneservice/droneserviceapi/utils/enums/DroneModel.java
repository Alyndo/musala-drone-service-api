package com.droneservice.droneserviceapi.utils.enums;

public enum DroneModel {

    LIGHTWEIGHT("LIGHTWEIGHT"), MIDDLEWEIGHT("MIDDLEWEIGHT"),
    CRUISERWEIGHT("CRUISERWEIGHT"), HEAVYWEIGHT("HEAVYWEIGHT");
    private String model;

    DroneModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public static DroneModel getEnumFromString(String model) {
        if( model != null ) {
            try {
                return DroneModel.valueOf(model.trim());
            }
            catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
