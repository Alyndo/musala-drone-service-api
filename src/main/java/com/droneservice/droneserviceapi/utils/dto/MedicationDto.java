package com.droneservice.droneserviceapi.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(NON_NULL)
public class MedicationDto {

    @Pattern(
            regexp = "[A-Z0-9_]+",
            message = "only upper case letters, underscore and numbers are allowed"
    )
    private String code;

    @Pattern(
            regexp = "[a-zA-Z_0-9-]+",
            message = "only letters, numbers, underscore and hyphen allowed"
    )
    private String name;

    private String image;

    @Positive
    private Integer weight;

}
