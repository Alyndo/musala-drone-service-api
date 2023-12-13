package com.droneservice.droneserviceapi.utils.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneStateChangeRequest {

    @NotBlank(message = "is a required parameter")
    @Size(min = 1, max = 100, message = "size must be between 1 and 100 characters long")
    private String serialNumber;

    @NotBlank(message = "is a required parameter")
    private String action;
}
