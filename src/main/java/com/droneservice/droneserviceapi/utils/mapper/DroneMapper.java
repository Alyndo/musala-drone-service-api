package com.droneservice.droneserviceapi.utils.mapper;

import com.droneservice.droneserviceapi.entity.Drone;
import com.droneservice.droneserviceapi.utils.dto.DroneDto;
import com.droneservice.droneserviceapi.utils.request.DroneRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface DroneMapper {
    Drone map(DroneRegistrationRequest droneRegistrationRequest);

//    @Mapping(target = "medicationDtos", source = "medications")
    @Mapping(target = "medicationDtos", source = "medications")
    DroneDto map(Drone drone);
}
