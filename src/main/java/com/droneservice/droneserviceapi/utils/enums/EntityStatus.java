package com.droneservice.droneserviceapi.utils.enums;

public enum EntityStatus {

    ACTIVE("ACTIVE"), DELETED("DELETED");

    private String status;

    EntityStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static EntityStatus getEnumFromString(String status) {
        if( status != null ) {
            try {
                return EntityStatus.valueOf(status.trim());
            }
            catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
