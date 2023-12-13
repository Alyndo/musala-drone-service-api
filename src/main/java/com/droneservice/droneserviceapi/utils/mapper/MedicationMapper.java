package com.droneservice.droneserviceapi.utils.mapper;

import com.droneservice.droneserviceapi.entity.Medication;
import com.droneservice.droneserviceapi.utils.dto.MedicationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    Medication map(MedicationDto medicationDto);

    MedicationDto map(Medication medication);
}