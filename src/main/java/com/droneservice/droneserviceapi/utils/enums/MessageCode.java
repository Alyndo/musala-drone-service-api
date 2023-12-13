package com.droneservice.droneserviceapi.utils.enums;

public enum MessageCode {

    MESSAGE_INVALID_REQUEST_SUPPLIED("messages.invalid.request.supplied"),
    MESSAGE_DRONE_ALREADY_EXISTS("messages.drone.already.exists"),
    MESSAGE_DRONE_REGISTRATION_SUCCESS("messages.drone.registration.success"),

    MESSAGE_DRONE_REGISTERED_EXCEEDED_LIMIT("message.drone.registered.exceeded.limit"),

    MESSAGE_DRONE_INVALID_DRONE_MODEL_SUPPLIED("messages.drone.invalid.drone.model.supplied"),
    MESSAGE_DRONE_FLEET_SIZE_VIOLATION("messages.drone.fleet.size.violation"),
    MESSAGE_DRONE_NOT_FOUND("messages.drone.not.found"),
    MESSAGE_DRONE_RETRIEVED_SUCCESSFULLY("message.drone.retrieved.success"),
    MESSAGE_DRONE_WEIGHT_LIMIT_VIOLATION("message.drone.weight.limit.violation"),
    MESSAGE_DRONE_BATTERY_LOW("message.drone.battery.low"),
    MESSAGE_DRONE_LOADED_SUCCESSFULLY("message.drone.loaded.success"),
    MESSAGE_DRONE_BATTERY_LEVEL_RETRIEVED_SUCCESSFULLY("message.drone.battery.level.retrieved.successfully"),
    MESSAGE_DRONE_INVALID_DRONE_ACTION_SUPPLIED("messages.drone.invalid.drone.action.supplied"),
    MESSAGE_DRONE_WRONG_METHOD("message.drone.incorrect.implementation"),
    MESSAGE_DRONE_NOT_AVAILABLE_FOR_LOADING("message.drone.not.available.for.loading");

    private String code;
    MessageCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
