package com.droneservice.droneserviceapi.utils.response;

import com.droneservice.droneserviceapi.utils.dto.DroneDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DroneResponse extends CommonResponse {
    private DroneDto droneDto;
    private List<DroneDto> droneDtos;
}
