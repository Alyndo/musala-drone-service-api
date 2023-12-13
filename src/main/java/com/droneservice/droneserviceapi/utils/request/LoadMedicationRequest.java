package com.droneservice.droneserviceapi.utils.request;

import com.droneservice.droneserviceapi.utils.dto.MedicationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadMedicationRequest {

    @Valid
    private List<MedicationDto> medicationDtos;
}
